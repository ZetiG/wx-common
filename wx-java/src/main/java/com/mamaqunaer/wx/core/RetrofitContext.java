package com.mamaqunaer.wx.core;

/**
 * Description: retrofit上下文
 *
 * @Date 2020/4/27 9:32
 * @Author Zeti
 */
public class RetrofitContext extends RetrofitNamedContextFactory<RetrofitSpecification> {

    public RetrofitContext() {
        super(RetrofitClientConfiguration.class, "retrofit", "retrofit.client.name");
    }
}
