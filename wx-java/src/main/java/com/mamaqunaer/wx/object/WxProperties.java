package com.mamaqunaer.wx.object;

import java.io.Serializable;

/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/23 16:31
 * @Author Zeti
 */
public class WxProperties implements Serializable {

    private static final long serialVersionUID = 2355737574260978119L;

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

    public WxProperties(String appid, String secret, String grant_type) {
        this.appid = appid;
        this.secret = secret;
        this.grant_type = grant_type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
