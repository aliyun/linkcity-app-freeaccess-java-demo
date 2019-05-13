package com.aliyun.iotx.city.demos.app.freeaccess.common.util;

/**
 * response公共部分封装
 * @Author 安悟
 * @Date 2018/6/29 下午3:32
 */
public class CommonApiResponse {
	private String id;
	private int code;
	private String message;
	private String localizedMsg;
	private Object data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocalizedMsg() {
		return localizedMsg;
	}

	public void setLocalizedMsg(String localizedMsg) {
		this.localizedMsg = localizedMsg;
	}

	public <T> T getData() {
		return (T)data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
