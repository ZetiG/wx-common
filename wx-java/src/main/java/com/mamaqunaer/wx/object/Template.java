package com.mamaqunaer.wx.object;


import java.io.Serializable;

/**
 * Description: 模板消息类
 *
 * @Date 2020/4/23 17:15
 * @Author Zeti
 */
public class Template implements Serializable {
    private static final long serialVersionUID = -7308822319517879409L;

    /**
     * 添加至帐号下的模板id，发送小程序订阅消息时所需
     */
    private String priTmplId;

    /**
     * 模版标题
     */
    private String title;

    /**
     * 模版内容
     */
    private String content;

    /**
     * 模板内容示例
     */
    private String example;

    /**
     * 模版类型，2 为一次性订阅，3 为长期订阅
     */
    private Integer type;


    public Template() {
    }

    public Template(String priTmplId, String title, String content, String example, Integer type) {
        this.priTmplId = priTmplId;
        this.title = title;
        this.content = content;
        this.example = example;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPriTmplId() {
        return priTmplId;
    }

    public void setPriTmplId(String priTmplId) {
        this.priTmplId = priTmplId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
