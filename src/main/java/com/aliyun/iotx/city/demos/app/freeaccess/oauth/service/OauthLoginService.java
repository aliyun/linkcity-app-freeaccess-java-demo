package com.aliyun.iotx.city.demos.app.freeaccess.oauth.service;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.city.demos.app.freeaccess.common.config.AppEnvParam;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.CommonApiResponse;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.JsonUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.ResponseUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean.OauthToken;
import com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean.OauthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Author 安悟
 * @Date 2018/8/13 下午9:33
 */
@Service
public class OauthLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthLoginService.class);

    @Autowired
    AppEnvParam environment;
    @Autowired
    SyncApiClient syncClient;

    public OauthToken getOauthToken(String authCode, String grantType, String redirectUrl, String clientId) {
        IoTApiRequest request = new IoTApiRequest();
        request.setApiVer("0.1.2");
        // 接口参数
        request.putParam("authCode", authCode);
        request.putParam("grantType", grantType);
        request.putParam("redirectUrl", redirectUrl);
        request.putParam("clientId", clientId);

        LOGGER.info("===request: {}", request);

        //请求参数域名、path、request
        ApiResponse response;
        try {
            response = syncClient.postBody(environment.getHost(), "/auth/token/get", request);

            if (null != response) {
                int statusCode = response.getStatusCode();
                if (200 == statusCode) {
                    CommonApiResponse resp = ResponseUtil.resolveResponse(response);
                    if (200 == resp.getCode()) {
                        return JsonUtil.jsonString2Object(resp.getData().toString(), OauthToken.class);
                    } else {
                        LOGGER.error("get data failed, reason: {}", resp.getLocalizedMsg());
                    }
                } else {
                    LOGGER.error("get data failed");
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        }
        return null;
    }

    public OauthUser getAuthUser(String accessToken, String userId, String clientId) {
        IoTApiRequest request = new IoTApiRequest();
        request.setApiVer("0.1.2");
        // 接口参数
        request.putParam("accessToken", accessToken);
        request.putParam("userId", userId);
        request.putParam("clientId", clientId);

        LOGGER.info("===request: {}", request);

        //请求参数域名、path、request
        ApiResponse response;
        try {
            response = syncClient.postBody(environment.getHost(), "/auth/userinfo/get", request);

            if (null != response) {
                int statusCode = response.getStatusCode();
                if (200 == statusCode) {
                    CommonApiResponse resp = ResponseUtil.resolveResponse(response);
                    if (200 == resp.getCode()) {
                        return JsonUtil.jsonString2Object(resp.getData().toString(), OauthUser.class);
                    } else {
                        LOGGER.error("get data failed, reason: {}", resp.getLocalizedMsg());
                    }
                } else {
                    LOGGER.error("get data failed");
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        }
        return null;
    }
}
