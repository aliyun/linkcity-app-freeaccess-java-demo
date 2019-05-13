package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

/**
 * @Author 安悟
 * @Date 2018/7/3 下午7:42
 */
public class DeviceInfo {
	private long latestOnlineTime;
	private int subDeviceCount;
	private long activeTime;
	private String clientIp;
	private Device deviceDTO;
	private Product productDTO;

	public long getLatestOnlineTime() {
		return latestOnlineTime;
	}

	public void setLatestOnlineTime(long latestOnlineTime) {
		this.latestOnlineTime = latestOnlineTime;
	}

	public int getSubDeviceCount() {
		return subDeviceCount;
	}

	public void setSubDeviceCount(int subDeviceCount) {
		this.subDeviceCount = subDeviceCount;
	}

	public long getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Device getDeviceDTO() {
		return deviceDTO;
	}

	public void setDeviceDTO(Device deviceDTO) {
		this.deviceDTO = deviceDTO;
	}

	public Product getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(Product productDTO) {
		this.productDTO = productDTO;
	}
}
