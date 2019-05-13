package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

import java.util.Date;
import java.util.Map;

/**
 * @Author 安悟
 * @Date 2018/7/5 下午8:39
 */
public class EventValue {
	private String eventCode;
	private String eventName;
	private String iotId;
	private String eventType;
	private long timestamp;
	private String batchId;
	private Map<String, Object> eventBody;

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getIotId() {
		return iotId;
	}

	public void setIotId(String iotId) {
		this.iotId = iotId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getTimestamp() {
		return new Date(timestamp);
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Map<String, Object> getEventBody() {
		return eventBody;
	}

	public void setEventBody(Map<String, Object> eventBody) {
		this.eventBody = eventBody;
	}
}
