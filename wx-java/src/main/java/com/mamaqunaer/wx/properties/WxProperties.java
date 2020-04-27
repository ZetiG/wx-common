package com.mamaqunaer.wx.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 常量类
 *
 * @Date 2020/4/23 16:30
 * @Author Zeti
 */
@Component
@ConfigurationProperties("wx.config")
public class WxProperties {

    /**
     * url
     */
    private String url;

    /**
     * appId
     */
    private String appid;

    /**
     * appSecret
     */
    private String secret;

    /**
     * 登录时获取的 code
     */
    private String js_code;

    /**
     * 授权类型，此处只需填写 authorization_code
     */
    private String grant_type;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getJs_code() {
        return js_code;
    }

    public void setJs_code(String js_code) {
        this.js_code = js_code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
