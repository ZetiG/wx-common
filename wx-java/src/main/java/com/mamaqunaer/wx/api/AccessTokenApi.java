package com.mamaqunaer.wx.api;

import com.mamaqunaer.wx.annotation.RetrofitClient;
import com.mamaqunaer.wx.interceptor.InterceptorLogConfig;
import com.mamaqunaer.wx.object.AccessTokenReturn;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description: 获取access_token
 *
 * @Date 2020/4/23 16:36
 * @Author Zeti
 */
@RetrofitClient(value = "accessTokenApi", url = "https://api.weixin.qq.com", interceptor = InterceptorLogConfig.class)
public interface AccessTokenApi {

    /**
     * 获取access_token
     *
     * @param appid      appId
     * @param secret     secret
     * @param grant_type 填写client_credential
     * @return AccessTokenReturn
     */
    @GET("/cgi-bin/token")
    Call<AccessTokenReturn> getAccessToken(@Query("appid") String appid,
                                           @Query("secret") String secret,
                                           @Query("grant_type") String grant_type);

}
