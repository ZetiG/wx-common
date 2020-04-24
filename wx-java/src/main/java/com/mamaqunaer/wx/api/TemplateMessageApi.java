package com.mamaqunaer.wx.api;

import com.mamaqunaer.wx.object.BaseReturn;
import com.mamaqunaer.wx.object.TemplateAdd;
import com.mamaqunaer.wx.object.TemplateAddReturn;
import com.mamaqunaer.wx.object.TemplateSendProperties;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description: 订阅消息接口
 *
 * @Date 2020/4/23 11:05
 * @Author Zeti
 */
public interface TemplateMessageApi {

    /**
     * 发送消息
     *
     * @param sendProperties 发送模板消息类
     * @return BaseReturn
     */
    @POST("/cgi-bin/message/subscribe/send")
    BaseReturn sendTemplate(TemplateSendProperties sendProperties);

    /**
     * 获取模板列表
     *
     * @param access_token 接口调用凭证
     * @return BaseReturn
     */
    @GET("/wxaapi/newtmpl/gettemplate")
    BaseReturn getTemplateList(String access_token);

    /**
     * 新增模板
     *
     * @param templateAdd 模板新增类
     * @return TemplateAddReturn
     */
    @POST("/wxaapi/newtmpl/addtemplate")
    TemplateAddReturn addTemplate(TemplateAdd templateAdd);

    /**
     * 删除模板
     *
     * @param access_token 接口调用凭证
     * @param priTmplId    要删除的模板id
     * @return BaseReturn
     */
    @POST("/wxaapi/newtmpl/deltemplate")
    BaseReturn deleteTemplate(String access_token, String priTmplId);

    /**
     * 获取小程序账号的类目
     *
     * @param access_token 接口调用凭证
     * @return BaseReturn
     */
    @GET("/wxaapi/newtmpl/getcategory")
    BaseReturn getCategory(String access_token);

}
