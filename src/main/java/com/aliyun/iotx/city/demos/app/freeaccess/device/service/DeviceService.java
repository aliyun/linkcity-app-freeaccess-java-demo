package com.aliyun.iotx.city.demos.app.freeaccess.device.service;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.city.demos.app.freeaccess.common.config.AppEnvParam;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.CommonApiResponse;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.JsonUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.ResponseUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.DeviceInfo;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.EventValue;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.ProductAbility;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.PropertyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 安悟
 * @Date 2018/7/3 下午6:04
 */
@Service
public class DeviceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

	@Autowired
	AppEnvParam environment;
	@Autowired
    SyncApiClient syncClient;

	@Value("${app.apiVersion.device}")
	private String deviceApiVersion;

	/**
	 * 获取数量
	 * @param productKey
	 * @param status
	 * @return
	 */
	public int getAuthorizedDeviceCount(String productKey, Integer status) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("pageNo", 1);
		request.putParam("pageSize", 1);
		request.putParam("status", status);
		request.putParam("productKey", productKey);
		// request.putParam("deviceName",deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/list", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						Object data = resp.getData();
						if (data instanceof Map) {
							Map<String, Object> dataMap = (Map<String, Object>)data;
							return (Integer) dataMap.get("totalNum");
						} else {
							return 0;
						}
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
		return 0;
	}

	/**
	 * 获取授权的设备信息
	 * @param productKey
	 * @param status
	 * @return
	 */
	public List<DeviceInfo> getAuthorizedDeviceInfo(String productKey, Integer status) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("pageNo", 1);
		request.putParam("pageSize", 10);
		request.putParam("status", status);
		request.putParam("productKey", productKey);
		// request.putParam("deviceName",deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/list", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						Object data = resp.getData();
						if (data instanceof Map) {
							Map<String, Object> dataMap = (Map<String, Object>)data;
							return JsonUtil.jsonString2List(dataMap.get("items").toString(), DeviceInfo.class);
						} else {
							return null;
						}
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

	/**
	 * 获取设备详情
	 * @param productKey
	 * @param deviceName
	 * @return
	 */
	public DeviceInfo getDeviceInfo(String productKey, String deviceName) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("productKey", productKey);
		request.putParam("deviceName", deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response = null;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/get", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						return JsonUtil.jsonString2Object(resp.getData().toString(), DeviceInfo.class);
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

	/**
	 * 查询产品的能力模型
	 * @param productKey
	 * @return
	 */
	public List<ProductAbility> getProductAbility(String productKey) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("productKey", productKey);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response;
		try {
			response = syncClient.postBody(environment.getHost(), "/product/ability/list", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						Object data = resp.getData();
						if (data instanceof List) {
							return JsonUtil.jsonString2List(data.toString(), ProductAbility.class);
						} else {
							return null;
						}
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

	/**
	 * 获取设备属性上送数据
	 * @param productKey
	 * @param deviceName
	 * @param propertyNames
	 * @return
	 */
	public List<PropertyValue> getDevicePropertys(String productKey, String deviceName, List<String> propertyNames) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("propertyNames", JSON.toJSON(propertyNames));
		request.putParam("deviceName", deviceName);
		request.putParam("productKey", productKey);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response = null;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/property/list", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						Object data = resp.getData();
						if (data instanceof List) {
							return JsonUtil.jsonString2List(data.toString(), PropertyValue.class);
						} else {
							return null;
						}
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

	/**
	 * 设置设备属性值
	 * @param productKey
	 * @param deviceName
	 * @param properties
	 * @return
	 */
	public boolean setDeviceProperty(String productKey, String deviceName, Map<String, Object> properties) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("attributes", JSON.toJSON(properties));
		request.putParam("productKey", productKey);
		request.putParam("deviceName", deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response = null;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/property/set", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						return true;
					} else {
						LOGGER.error("set property failed, reason: {}", resp.getLocalizedMsg());
					}
				} else {
					LOGGER.error("set property failed");
				}
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
		}
		return false;
	}

	public boolean serviceInvoke(String productKey, String deviceName, String serviceName, Map<String, Object> args) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("attributes", JSON.toJSON(args));
		request.putParam("serviceName", serviceName);
		request.putParam("productKey", productKey);
		request.putParam("deviceName", deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/service/invoke", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						return true;
					} else {
						LOGGER.error("invoke service failed, reason: {}", resp.getLocalizedMsg());
					}
				} else {
					LOGGER.error("invoke service failed");
				}
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
		}
		return false;
	}

	/**
	 * 获取设备指定事件最近一次的记录
	 * @param productKey
	 * @param deviceName
	 * @param eventCode
	 * @return
	 */
	public EventValue getDeviceEventLastRecord(String productKey, String deviceName, String eventCode) {
		// 接口参数
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		request.putParam("eventCode", eventCode);
		request.putParam("productKey", productKey);
		request.putParam("deviceName", deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/event/last", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						return JsonUtil.jsonString2Object(resp.getData().toString(), EventValue.class);
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

	/**
	 * 获取设备指定事件的历史记录
	 * @param productKey
	 * @param deviceName
	 * @param eventCode
	 * @param eventType
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EventValue> batchQueryDeviceEvent(String productKey, String deviceName, String eventCode, String eventType, Date start, Date end) {
		IoTApiRequest request = new IoTApiRequest();
		request.setApiVer(deviceApiVersion);
		// 接口参数
		request.putParam("eventCode", eventCode);
		request.putParam("ordered", false);
		request.putParam("start", start.getTime());
		request.putParam("end", end.getTime());
		request.putParam("pageSize", 10);
		request.putParam("eventType", eventType);
		request.putParam("productKey", productKey);
		request.putParam("deviceName", deviceName);

		LOGGER.info("===request: {}", request);

		//请求参数域名、path、request
		ApiResponse response = null;
		try {
			response = syncClient.postBody(environment.getHost(), "/device/event/list", request);

			if (null != response) {
				int statusCode = response.getStatusCode();
				if (200 == statusCode) {
					CommonApiResponse resp = ResponseUtil.resolveResponse(response);
					if (200 == resp.getCode()) {
						Object data = resp.getData();
						if (data instanceof Map) {
							Map<String, Object> dataMap = (Map<String, Object>)data;
							return JsonUtil.jsonString2List(dataMap.get("items").toString(), EventValue.class);
						} else {
							return null;
						}
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
