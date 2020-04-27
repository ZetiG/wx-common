package com.mamaqunaer.wx.core;

import com.mamaqunaer.wx.annotation.EnableRetrofitClient;
import com.mamaqunaer.wx.annotation.RetrofitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/24 16:25
 * @Author Zeti
 */
public class RetrofitClientAutoRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware,
        EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitClientAutoRegister.class);

    private ResourceLoader resourceLoader;

    private Environment environment;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 动态注册bean
     *
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        logger.debug("Searching for retrofit client annotated with @RetrofitClient");

        // 扫描器
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        // 扫描基础包下标注注解接口
        scanner.addIncludeFilter(new AnnotationTypeFilter(RetrofitClient.class));
        //
        Set<String> basePackages = getBasePackages(importingClassMetadata);

        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // 校验注解作用的类是否是接口
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isInterface(),
                            "@RetrofitClient can only be specified on an interface");

                    Map<String, Object> attributes = annotationMetadata
                            .getAnnotationAttributes(RetrofitClient.class.getCanonicalName());
                    if (attributes != null) {
                        String name = getClientName(attributes);
                        //注册配置类
                        registerClientConfiguration(registry, name, attributes.get("configuration"));
                        //注册拦截器
                        registerClientConfiguration(registry, name, attributes.get("interceptor"));
                    }
                    // 调用registerHttpClient注册类定义
                    registerRetrofitClient(registry, annotationMetadata, attributes);
                }
            }
        }
    }

    private void registerRetrofitClient(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata,
                                    Map<String, Object> attributes) {
//        validate(attributes);

        String className = annotationMetadata.getClassName();
        // 在RetrofitClientFactoryBean中设置url type name..等信息
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(RetrofitClientFactoryBean.class);
        definition.addPropertyValue("url", getUrl(attributes));
        definition.addPropertyValue("type", className);
        String name = getName(attributes);
        definition.addPropertyValue("name", name);
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

        boolean primary = (Boolean) attributes.get("primary");

        beanDefinition.setPrimary(primary);

        String qualifier = getQualifier(attributes);
        String alias = className + "RetrofitApi";
        if (StringUtils.hasText(qualifier)) {
            alias = qualifier;
        }

        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className, new String[]{alias});
        // 针对每一个接口注册一个FactoryBean
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    /**
     * 组装Configuration、拦截器
     *
     * @param registry
     * @param name
     * @param configuration
     */
    private void registerClientConfiguration(BeanDefinitionRegistry registry, Object name, Object configuration) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RetrofitSpecification.class);
        builder.addConstructorArgValue(name);
        builder.addConstructorArgValue(configuration);
        registry.registerBeanDefinition(name + "." + RetrofitSpecification.class.getSimpleName(),
                builder.getBeanDefinition());
    }

    /**
     * 构造Class扫描器
     */
    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isInterface()) {
                    return !beanDefinition.getMetadata().isAnnotation();
                }
                return false;
            }
        };
    }

    /**
     * 获取@EnableRetrofitClient注解作用包名
     *
     * @param importingClassMetadata
     * @return
     */
    private Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableRetrofitClient.class.getCanonicalName());
        if (attributes == null) return Collections.emptySet();

        Set<String> basePackages = new HashSet<>();
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : (Class[]) attributes.get("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }

    private String getName(Map<String, Object> attributes) {

        String name = (String) attributes.get("name");
        if (!StringUtils.hasText(name)) {
            name = (String) attributes.get("value");
        }
        name = resolve(name);
        if (!StringUtils.hasText(name)) {
            return "";
        }

        String host;
        try {
            String url;
            if (!name.startsWith("http://") && !name.startsWith("https://")) {
                url = "http://" + name;
            } else {
                url = name;
            }
            host = new URI(url).getHost();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("URL解析失败");
        }
        Assert.state(host != null, "Service id not legal hostname (" + name + ")");
        return name;
    }

    private String resolve(String value) {
        if (StringUtils.hasText(value)) {
            return this.environment.resolvePlaceholders(value);
        }
        return value;
    }

    private String getUrl(Map<String, Object> attributes) {
        String url = resolve((String) attributes.get("url"));
        return getUrl(url);
    }

    private static String getUrl(String url) {
        if (StringUtils.hasText(url) && !(url.startsWith("#{") && url.contains("}"))) {
            if (!url.contains("://")) {
                url = "http://" + url;
            }
            try {
                new URL(url);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(url + " is malformed", e);
            }
        }
        return url;
    }

    private String getQualifier(Map<String, Object> attributes) {
        if (attributes == null) {
            return null;
        }
        String qualifier = (String) attributes.get("qualifier");
        if (StringUtils.hasText(qualifier)) {
            return qualifier;
        }
        return null;
    }

    private String getClientName(Map<String, Object> client) {
        if (client == null) {
            return null;
        }
        String value = (String) client.get("value");
        if (StringUtils.hasText(value)) {
            return value;
        }
        throw new IllegalStateException("Either 'name' or 'value' must be provided in @" + RetrofitClient.class.getSimpleName());
    }


}
