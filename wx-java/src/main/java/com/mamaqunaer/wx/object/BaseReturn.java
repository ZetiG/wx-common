package com.mamaqunaer.wx.object;

import java.io.Serializable;

/**
 * Description: 返回结果基类
 *
 * @Date 2020/4/23 17:00
 * @Author Zeti
 */
public class BaseReturn implements Serializable {

    private static final long serialVersionUID = -767571623878247984L;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 返回信息
     */
    private Object data;


    public BaseReturn() {
    }

    public BaseReturn(Integer errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
