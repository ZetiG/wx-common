package com.mamaqunaer.wx.object;


import java.io.Serializable;
import java.util.List;

/**
 * Description: 新增模板类(用一句话描述该文件做什么)
 *
 * @Date 2020/4/23 17:45
 * @Author Zeti
 */
public class TemplateAdd implements Serializable {
    private static final long serialVersionUID = 7043793986194376453L;

    /**
     * 接口调用凭证，必填：是
     */
    private String access_token;

    /**
     * 模板标题 id，可通过接口获取，也可登录小程序后台查看获取
     * 必填：是
     */
    private String tid;

    /**
     * 开发者自行组合好的模板关键词列表，关键词顺序可以自由搭配
     * (例如 [3,5,4] 或 [4,5,3])，最多支持5个，最少2个关键词组合
     * 必填：是
     */
    private List<Integer> kidList;

    /**
     * 服务场景描述，15个字以内
     * 必填：否
     */
    private String sceneDesc;

    public TemplateAdd() {
    }

    public TemplateAdd(String access_token, String tid, List<Integer> kidList, String sceneDesc) {
        this.access_token = access_token;
        this.tid = tid;
        this.kidList = kidList;
        this.sceneDesc = sceneDesc;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public List<Integer> getKidList() {
        return kidList;
    }

    public void setKidList(List<Integer> kidList) {
        this.kidList = kidList;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc;
    }
}
