package com.mamaqunaer.wx.core;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Description:
 *
 * @Date 2020/4/26 11:29
 * @Author Zeti
 */
@Configuration
public class RetrofitClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient.Builder okHttpClient() {
        return new OkHttpClient.Builder();
    }


    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * Scalars转换
     *
     * @return
     */
    @Bean
    public Converter.Factory ScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    /**
     * Gson转换
     *
     * @return
     */
    @Bean
    public Converter.Factory GsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .setLenient()
                .create()
        );
    }
}
