package com.mamaqunaer.wx.object;

import java.io.Serializable;

/**
 * Description: 微信配置类
 *
 * @Date 2020/4/23 16:31
 * @Author Zeti
 */
public class WxProperties implements Serializable {

    private static final long serialVersionUID = 2355737574260978119L;

    /**
     * 微信url
     */
    private String url;

    /**
     * appId
     */
    private String appid;

    /**
     * secret
     */
    private String secret;

    /**
     * 请求类型，小程序用：client_credential
     */
    private String grant_type;

    public WxProperties() {
    }

    public WxProperties(String url, String appid, String secret, String grant_type) {
        this.url = url;
        this.appid = appid;
        this.secret = secret;
        this.grant_type = grant_type;
    }

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

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
