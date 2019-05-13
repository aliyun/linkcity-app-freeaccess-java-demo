package com.aliyun.iotx.city.demos.app.freeaccess.common.config;

import com.aliyun.iotx.api.client.SyncApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义配置
 * @Author 安悟
 * @Date 2018/6/29 下午5:53
 */
@Configuration
public class CommonConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public SyncApiClient getSyncApiClient(AppEnvParam environment) {
		SyncApiClient syncClient = SyncApiClient.newBuilder()
				.appKey(environment.getAppkey())
				.appSecret(environment.getAppsecret())
				.build();
		return syncClient;
	}

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse(source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}
}
