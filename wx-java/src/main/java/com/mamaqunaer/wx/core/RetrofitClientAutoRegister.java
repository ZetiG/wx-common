package com.mamaqunaer.wx.core;

import com.mamaqunaer.wx.annotation.RetrofitClient;
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
        // 扫描器
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        // 扫描基础包下标注注解接口
        scanner.addIncludeFilter(new AnnotationTypeFilter(RetrofitClient.class));
        String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
        for (BeanDefinition candidateComponent : candidateComponents) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                Map<String, Object> attributes =
                        annotationMetadata.getAnnotationAttributes(RetrofitClient.class.getCanonicalName());
                // 调用registerHttpClient注册类定义
                registerHttpClient(registry, annotationMetadata, attributes);
            }
        }
    }

    private void registerHttpClient(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata,
                                    Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();
        // 在RetrofitClientFactoryBean中设置url type name..等信息
//        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(RetrofitClientFactoryBean.class);
//        definition.addPropertyValue("url", getUrl(attributes));
//        definition.addPropertyValue("type", className);
//        String name = getName(attributes);
//        definition.addPropertyValue("name", name);
//        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
//
//        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
//        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className, null);
        // 针对每一个接口注册一个FactoryBean
//        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
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

}
