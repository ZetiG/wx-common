package com.mamaqunaer.wx.api;

import com.mamaqunaer.wx.object.BaseReturn;
import com.mamaqunaer.wx.object.TemplateAdd;
import com.mamaqunaer.wx.object.TemplateAddReturn;
import com.mamaqunaer.wx.object.TemplateSendProperties;

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
     * @return result
     */
    BaseReturn sendTemplate(TemplateSendProperties sendProperties);

    /**
     * 获取模板列表
     *
     * @param access_token 接口调用凭证
     * @return result
     */
    BaseReturn getTemplateList(String access_token);

    /**
     * 新增模板
     *
     * @param templateAdd 模板新增类
     * @return result
     */
    TemplateAddReturn addTemplate(TemplateAdd templateAdd);

    /**
     * 删除模板
     *
     * @param access_token 接口调用凭证
     * @param priTmplId    要删除的模板id
     * @return result
     */
    BaseReturn deleteTemplate(String access_token, String priTmplId);

    /**
     * 获取小程序账号的类目
     *
     * @param access_token 接口调用凭证
     * @return result
     */
    BaseReturn getCategory(String access_token);

}
