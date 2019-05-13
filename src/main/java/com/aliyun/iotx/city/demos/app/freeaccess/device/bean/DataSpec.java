package com.aliyun.iotx.city.demos.app.freeaccess.device.bean;

/**
 * @Author 安悟
 * @Date 2018/7/4 下午7:43
 */
public class DataSpec {
	private long gmtModified;
	private String unitName;
	private String max;
	private boolean custom;
	private String dataType;
	private long gmtCreate;
	private String unit;
	private String min;
	private String step;
	private int id;
	private int precise;
	private Object value;
	private String name;
	private String identifier;
	private String childName;
	private DataSpec childSpecsDTO;

	public long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(long gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public long getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(long gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrecise() {
		return precise;
	}

	public void setPrecise(int precise) {
		this.precise = precise;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public DataSpec getChildSpecsDTO() {
		return childSpecsDTO;
	}

	public void setChildSpecsDTO(DataSpec childSpecsDTO) {
		this.childSpecsDTO = childSpecsDTO;
	}
}
