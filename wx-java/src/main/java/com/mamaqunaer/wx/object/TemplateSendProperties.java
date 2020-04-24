package com.mamaqunaer.wx.object;

import java.io.Serializable;

/**
 * Description: 发送模板消息类
 *
 * @Date 2020/4/23 16:53
 * @Author Zeti
 */
public class TemplateSendProperties implements Serializable {

    private static final long serialVersionUID = 148107265112916077L;

    /**
     * 接口调用凭证，必传:是
     */
    private String access_token;

    /**
     * 接收者（用户）的 openid，必传:是
     */
    private String touser;

    /**
     * 所需下发的订阅模板id，必传:是
     */
    private String template_id;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。
     * 支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转，
     * 必传:否
     */
    private String page;

    /**
     * 模板内容
     * 格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     * 必传:是
     */
    private Object data;

    /**
     * 跳转小程序类型：developer为开发版；
     * trial为体验版；formal为正式版；默认为正式版
     * 必传:否
     */
    private Object miniprogram_state;

    /**
     * 进入小程序查看”的语言类型，
     * 支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
     * 必传:否
     */
    private String lang;

    public TemplateSendProperties() {
    }

    public TemplateSendProperties(String access_token, String touser, String template_id, String page, Object data,
                                  Object miniprogram_state, String lang) {
        this.access_token = access_token;
        this.touser = touser;
        this.template_id = template_id;
        this.page = page;
        this.data = data;
        this.miniprogram_state = miniprogram_state;
        this.lang = lang;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMiniprogram_state() {
        return miniprogram_state;
    }

    public void setMiniprogram_state(Object miniprogram_state) {
        this.miniprogram_state = miniprogram_state;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
