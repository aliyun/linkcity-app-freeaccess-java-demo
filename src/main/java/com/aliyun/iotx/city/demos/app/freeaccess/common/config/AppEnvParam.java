package com.aliyun.iotx.city.demos.app.freeaccess.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 安悟
 * @Date 2018/6/29 下午5:47
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppEnvParam {
	private String appkey;
	private String appsecret;
	private String host;

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "IoTEnvironment{" +
				"appkey='" + appkey + '\'' +
				", appsecret='" + appsecret + '\'' +
				", host='" + host + '\'' +
				'}';
	}
}
