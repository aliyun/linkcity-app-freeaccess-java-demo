package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

/**
 * @Author 安悟
 * @Date 2018/7/4 下午8:49
 */
public class PropertyValue {
	private String attribute;
	private Object value;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
