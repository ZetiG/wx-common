package com.mamaqunaer.wx.interceptor;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 请求日志
 *
 * @Date 2020/4/24 11:34
 * @Author Zeti
 */
@Configuration
public class InterceptorLogConfig {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorLogConfig.class);

    /**
     * 请求日志
     *
     * @return
     */
    @Bean
    public Interceptor logInterceptor() {
        HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                if (message.startsWith("->") || message.startsWith("{")) logger.debug(message);
            }
        });
        interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptorBody;
    }

}
