package com.mamaqunaer.wx.object;


/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/23 16:39
 * @Author Zeti
 */
public class AccessTokenReturn extends BaseReturn {

    private static final long serialVersionUID = 1763297173167790864L;

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    private Integer expires_in;


    public AccessTokenReturn() {
    }

    public AccessTokenReturn(String access_token, Integer expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
