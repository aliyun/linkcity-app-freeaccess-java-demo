package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

/**
 * 产品
 * @Author 安悟
 * @Date 2018/7/3 下午7:35
 */
public class Product {
	private long gmtModified;
	private int netType;
	private long gmtCreate;
	private String productKey;
	private int nodeType;
	private String name;
	private int categoryId;
	private String description;

	public long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(long gmtModified) {
		this.gmtModified = gmtModified;
	}

	public int getNetType() {
		return netType;
	}

	public void setNetType(int netType) {
		this.netType = netType;
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

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
