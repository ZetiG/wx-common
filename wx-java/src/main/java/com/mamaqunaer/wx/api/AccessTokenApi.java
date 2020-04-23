package com.mamaqunaer.wx.api;

import com.mamaqunaer.wx.object.AccessTokenReturn;
import com.mamaqunaer.wx.object.WxProperties;

/**
 * Description: 获取access_token
 *
 * @Date 2020/4/23 16:36
 * @Author Zeti
 */
public interface AccessTokenApi {

    /**
     * 获取access_token
     *
     * @param wxProperties 获取微信token类
     * @return result
     */
    AccessTokenReturn getAccessToken(WxProperties wxProperties);

}
