package com.mamaqunaer.wx.annotation;

import java.lang.annotation.*;

/**
 * Description: 构建retrofit接口实例
 *
 * @Date 2020/4/24 15:15
 * @Author Zeti
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrofitClient {

    /**
     * bean name
     */
    String name() default "";

    /**
     * baseUrl地址
     */
    String url() default "";

    /**
     * client bean alias
     */
    String qualifier() default "";

    /**
     * 自定义配置类
     */
    Class<?>[] configuration() default {};

    /**
     * 配置拦截器
     */
    Class<?>[] interceptor() default {};


}
