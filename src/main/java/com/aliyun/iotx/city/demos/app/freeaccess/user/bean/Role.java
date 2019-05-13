package com.aliyun.iotx.city.demos.app.freeaccess.user.bean;

import java.util.Date;

/**
 * @Author 安悟
 * @Date 2018/7/2 下午9:18
 */
public class Role {
	private String rid;
	private String name;
	private String code;
	private Date createTime;
	private Date lastupdate;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	@Override
	public String toString() {
		return "Role{" +
				"rid=" + rid +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", createTime=" + createTime +
				", lastupdate=" + lastupdate +
				'}';
	}
}
