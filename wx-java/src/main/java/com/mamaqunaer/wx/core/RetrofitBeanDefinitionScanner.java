package com.mamaqunaer.wx.core;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;

/**
 * Description: 扫描需要注册的bean
 *  TODO: 2020/4/27 暂不用
 *
 * @Date 2020/4/24 16:43
 * @Author Zeti
 */
public class RetrofitBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    RetrofitBeanDefinitionScanner(Environment environment) {
        new ClassPathScanningCandidateComponentProvider(false, environment);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        if (beanDefinition.getMetadata().isInterface()) {
            return !beanDefinition.getMetadata().isAnnotation();
        }
        return false;
    }

}
