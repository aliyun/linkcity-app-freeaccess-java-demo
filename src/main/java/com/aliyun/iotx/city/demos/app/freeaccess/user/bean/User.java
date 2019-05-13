package com.aliyun.iotx.city.demos.app.freeaccess.user.bean;

import java.util.Date;

/**
 * 用户
 * @Author 安悟
 * @Date 2018/6/30 下午3:52
 */
public class User {
	private String uid;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Date birthday;
	private Date createTime;
	private Date lastupdate;

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
		return "User{" +
				"uid=" + uid +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				", birthday=" + birthday +
				", createTime=" + createTime +
				", lastupdate=" + lastupdate +
				'}';
	}
}
