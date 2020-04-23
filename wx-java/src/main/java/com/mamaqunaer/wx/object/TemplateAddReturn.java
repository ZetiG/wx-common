package com.mamaqunaer.wx.object;


import java.io.Serializable;

/**
 * Description: 新增模板返回类
 *
 * @Date 2020/4/23 17:49
 * @Author Zeti
 */
public class TemplateAddReturn implements Serializable {
    private static final long serialVersionUID = -7900685625471223480L;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 添加至帐号下的模板id，发送小程序订阅消息时所需
     */
    private String priTmplId;

    public TemplateAddReturn() {
    }

    public TemplateAddReturn(Integer errcode, String errmsg, String priTmplId) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.priTmplId = priTmplId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getPriTmplId() {
        return priTmplId;
    }

    public void setPriTmplId(String priTmplId) {
        this.priTmplId = priTmplId;
    }
}
