package com.mamaqunaer.wx;

import com.mamaqunaer.wx.api.AccessTokenApi;
import com.mamaqunaer.wx.api.TemplateMessageApi;
import com.mamaqunaer.wx.object.AccessTokenReturn;
import com.mamaqunaer.wx.object.BaseReturn;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Description: (用一句话描述该文件做什么)
 *
 * @Date 2020/4/24 10:20
 * @Author Zeti
 */
public class AccessTokenImplTest {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenImplTest.class);

    private String appid = "wxefb1c727022be3ca";
    private String secret = "b9d20b0f5d6d7c518b81e6c2943d7083";
    private String grant_type = "authorization_code";

    @Autowired
    private AccessTokenApi accessTokenApi;

    @Autowired
    private TemplateMessageApi templateMessageApi;


    public static void main(String[] args) {

        //实例化
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.weixin.qq.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AccessTokenApi accessTokenApi = retrofit.create(AccessTokenApi.class);

        //调用接口
//        try {
//
//            Call<AccessTokenReturn> accessToken = accessTokenApi.getAccessToken(appid, secret, grant_type);
//            Response<AccessTokenReturn> execute = accessToken.execute();
//            logger.info("execute_logger-> {}", execute.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Test
    public void test() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.weixin.qq.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AccessTokenApi accessTokenApi = retrofit.create(AccessTokenApi.class);
        //调用接口
        try {
            Call<BaseReturn> templateList = templateMessageApi.getTemplateList("");
            Call<AccessTokenReturn> accessToken = accessTokenApi.getAccessToken(appid, secret, grant_type);
            Response<AccessTokenReturn> execute = accessToken.execute();
            logger.info("execute_logger-> {}", execute.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
