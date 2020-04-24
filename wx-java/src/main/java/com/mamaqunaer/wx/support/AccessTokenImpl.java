package com.mamaqunaer.wx.support;

import com.mamaqunaer.wx.api.AccessTokenApi;
import com.mamaqunaer.wx.object.AccessTokenReturn;
import com.mamaqunaer.wx.object.WxProperties;
import retrofit2.Call;

/**
 * Description: 获取token接口
 *
 * @Date 2020/4/24 9:48
 * @Author Zeti
 */
public class AccessTokenImpl implements AccessTokenApi {


    @Override
    public Call<AccessTokenReturn> getAccessToken(String appid, String secret, String grant_type) {
        return null;
    }
}
