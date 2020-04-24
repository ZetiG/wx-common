package com.mamaqunaer.wx.api;

import com.mamaqunaer.wx.object.BaseReturn;
import com.mamaqunaer.wx.object.TemplateAdd;
import com.mamaqunaer.wx.object.TemplateAddReturn;
import com.mamaqunaer.wx.object.TemplateSendProperties;
import retrofit2.Call;
import retrofit2.http.*;

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
    Call<BaseReturn> sendTemplate(@Body TemplateSendProperties sendProperties);

    /**
     * 获取模板列表
     *
     * @param access_token 接口调用凭证
     * @return BaseReturn
     */
    @GET("/wxaapi/newtmpl/gettemplate")
    Call<BaseReturn> getTemplateList(@Query("access_token") String access_token);

    /**
     * 新增模板
     *
     * @param templateAdd 模板新增类
     * @return TemplateAddReturn
     */
    @POST("/wxaapi/newtmpl/addtemplate")
    Call<TemplateAddReturn> addTemplate(@Body TemplateAdd templateAdd);

    /**
     * 删除模板
     *
     * @param access_token 接口调用凭证
     * @param priTmplId    要删除的模板id
     * @return BaseReturn
     */
    @POST("/wxaapi/newtmpl/deltemplate")
    @FormUrlEncoded
    Call<BaseReturn> deleteTemplate(@Field("access_token") String access_token,
                              @Field("priTmplId") String priTmplId);

    /**
     * 获取小程序账号的类目
     *
     * @param access_token 接口调用凭证
     * @return BaseReturn
     */
    @GET("/wxaapi/newtmpl/getcategory")
    Call<BaseReturn> getCategory(@Query("access_token") String access_token);


}
