package com.mamaqunaer.wx.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: retrofit容器
 *
 * @Date 2020/4/27 9:43
 * @Author Zeti
 */
public abstract class RetrofitNamedContextFactory<C extends RetrofitNamedContextFactory.Specification>
        implements ApplicationContextAware, DisposableBean {

    /**
     * 定义retrofit上下文，AnnotationConfigApplicationContext是ApplicationContext的一个实现类
     */
    private Map<String, AnnotationConfigApplicationContext> contexts = new ConcurrentHashMap<>();

    /**
     * 自定义配置
     */
    private Map<String, C> configurations = new ConcurrentHashMap<>();

    /**
     * 父容器
     */
    private ApplicationContext parent;

    private Class<?> defaultConfigType;
    private final String propertySourceName;
    private final String propertyName;


    public RetrofitNamedContextFactory(Class<?> defaultConfigType, String propertySourceName,
                                       String propertyName) {
        this.defaultConfigType = defaultConfigType;
        this.propertySourceName = propertySourceName;
        this.propertyName = propertyName;
    }

    @Override
    public void setApplicationContext(ApplicationContext parent) throws BeansException {
        this.parent = parent;
    }

    /**
     * 获取retrofit上下文容器，不存在则创建
     *
     * @param name
     * @return
     */
    private AnnotationConfigApplicationContext getContext(String name) {
        if (!this.contexts.containsKey(name)) {
            this.contexts.put(name, createContext(name));
        }
        return this.contexts.get(name);
    }

    private AnnotationConfigApplicationContext createContext(String name) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        if (this.configurations.containsKey(name)) {
            for (Class<?> configuration : this.configurations.get(name).getConfiguration()) {
                context.register(configuration);
            }
        }

        context.register(PropertyPlaceholderAutoConfiguration.class, this.defaultConfigType);
        context.getEnvironment().getPropertySources().addFirst(new MapPropertySource(this.propertySourceName,
                Collections.singletonMap(this.propertyName, name)));
        if (this.parent != null) {
            context.setParent(this.parent);
        }
        context.refresh();
        return context;
    }

    public void setConfigurations(List<C> configurations) {
        for (C client : configurations) {
            this.configurations.put(client.getName(), client);
        }
    }

    public Set<String> getContextNames() {
        return new HashSet<>(contexts.keySet());
    }

    @Override
    public void destroy() {
        Collection<AnnotationConfigApplicationContext> values = this.contexts.values();
        for (AnnotationConfigApplicationContext context : values) {
            context.close();
        }
        this.contexts.clear();
    }

    public <T> T getInstance(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        if (BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, type).length > 0) {
            return context.getBean(type);
        }
        return null;
    }

    public <T> Map<String, T> getInstances(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        if (BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, type).length > 0) {
            return BeanFactoryUtils.beansOfTypeIncludingAncestors(context, type);
        }
        return null;
    }

    interface Specification {
        String getName();

        Class<?>[] getConfiguration();
    }

}
