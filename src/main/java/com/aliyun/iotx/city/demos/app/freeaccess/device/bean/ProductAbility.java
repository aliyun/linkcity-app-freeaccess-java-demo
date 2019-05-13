package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

import java.util.List;

/**
 * @Author 安悟
 * @Date 2018/7/4 下午7:43
 */
public class ProductAbility {
	private String abilityType;
	private String dataType;
	private String required;
	private String funId;
	private String categoryType;
	private Integer abilityId;
	private String funName;
	private String fundes;
	private String callType;
	private String eventType;

	private DataSpec dataSpecs;

	private List<DataSpec> dataSpecsList;

	public String getAbilityType() {
		return abilityType;
	}

	public void setAbilityType(String abilityType) {
		this.abilityType = abilityType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(Integer abilityId) {
		this.abilityId = abilityId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public DataSpec getDataSpecs() {
		return dataSpecs;
	}

	public void setDataSpecs(DataSpec dataSpecs) {
		this.dataSpecs = dataSpecs;
	}

	public List<DataSpec> getDataSpecsList() {
		return dataSpecsList;
	}

	public void setDataSpecsList(List<DataSpec> dataSpecsList) {
		this.dataSpecsList = dataSpecsList;
	}

	public String getFundes() {
		return fundes;
	}

	public void setFundes(String fundes) {
		this.fundes = fundes;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
}
