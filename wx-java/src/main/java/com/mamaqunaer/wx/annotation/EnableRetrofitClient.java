package com.mamaqunaer.wx.annotation;

import com.mamaqunaer.wx.core.RetrofitClientAutoRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description: 启用自定义Retrofit注解
 *
 * @Date 2020/4/24 18:44
 * @Author Zeti
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RetrofitClientAutoRegister.class)
public @interface EnableRetrofitClient {

    /**
     * 名称
     */
    String[] value() default {};

    /**
     * 扫描包路径
     */
    String[] basePackages() default {"com.mamaqunaer.wx"};

}
