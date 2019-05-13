package com.aliyun.iotx.city.demos.app.freeaccess.common.util;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * response处理工具类
 * @Author 安悟
 * @Date 2018/6/29 下午3:32
 */
public class ResponseUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

	/**
	 * api结果处理
	 * @param response
	 * @return
	 */
	public static CommonApiResponse resolveResponse(ApiResponse response) {
		CommonApiResponse resp = new CommonApiResponse();

		String responseBody;
		try {
			responseBody = new String(response.getBody(), "utf-8");
			LOGGER.debug("===response: {}", responseBody);
			Map<String, Object> objectMap = JSON.parseObject(responseBody, new TypeReference<Map<String, Object>>() {
			});
			resp.setCode(Integer.parseInt(objectMap.get("code").toString()));
			resp.setData(objectMap.get("data"));
			if (objectMap.containsKey("message")) {
				resp.setMessage(objectMap.get("message").toString());
			}
			if (objectMap.containsKey("localizedMsg")) {
				resp.setLocalizedMsg(objectMap.get("localizedMsg").toString());
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
		}

		return resp;
	}
}
