package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

/**
 * 设备
 * @Author 安悟
 * @Date 2018/7/3 下午7:35
 */
public class Device {
	private long gmtModified;
	private long activeTime;
	private String deviceKey;
	private long gmtCreate;
	private String productKey;
	private String deviceSecret;
	private String iotId;
	private String name;
	private String thingType;
	private int status;

	public long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(long gmtModified) {
		this.gmtModified = gmtModified;
	}

	public long getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public long getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(long gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getDeviceSecret() {
		return deviceSecret;
	}

	public void setDeviceSecret(String deviceSecret) {
		this.deviceSecret = deviceSecret;
	}

	public String getIotId() {
		return iotId;
	}

	public void setIotId(String iotId) {
		this.iotId = iotId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThingType() {
		return thingType;
	}

	public void setThingType(String thingType) {
		this.thingType = thingType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Device{" +
				"gmtModified='" + gmtModified + '\'' +
				", activeTime=" + activeTime +
				", deviceKey='" + deviceKey + '\'' +
				", gmtCreate=" + gmtCreate +
				", productKey='" + productKey + '\'' +
				", deviceSecret='" + deviceSecret + '\'' +
				", iotId='" + iotId + '\'' +
				", name='" + name + '\'' +
				", thingType='" + thingType + '\'' +
				", status=" + status +
				'}';
	}
}
