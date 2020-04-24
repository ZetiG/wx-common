package com.mamaqunaer.wx;

import com.mamaqunaer.wx.object.AccessTokenReturn;
import com.mamaqunaer.wx.object.WxProperties;
import com.mamaqunaer.wx.support.AccessTokenImpl;

/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/24 10:20
 * @Author Zeti
 */
public class AccessTokenImplTest {


    public static void main(String[] args) {
        AccessTokenImpl accessToken = new AccessTokenImpl();
        WxProperties wxProperties = new WxProperties();
        wxProperties.setUrl("https://api.weixin.qq.com");
        wxProperties.setAppid("wxefb1c727022be3ca");
        wxProperties.setSecret("b9d20b0f5d6d7c518b81e6c2943d7083");
        wxProperties.setGrant_type("authorization_code");
        AccessTokenReturn accessToken1 = accessToken.getAccessToken(wxProperties);
        System.out.println(accessToken1);

    }


}
