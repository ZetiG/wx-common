package com.mamaqunaer.wx.config;

import com.mamaqunaer.wx.annotation.EnableRetrofitClient;
import org.springframework.context.annotation.Configuration;

/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/27 18:23
 * @Author Zeti
 */
@Configuration
@EnableRetrofitClient(basePackages = "com.mamaqunaer.wx")
public class WxRetrofitConfiguration {

}
