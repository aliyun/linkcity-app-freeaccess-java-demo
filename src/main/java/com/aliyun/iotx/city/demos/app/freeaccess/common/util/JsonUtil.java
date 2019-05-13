package com.aliyun.iotx.city.demos.app.freeaccess.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @Author 安悟
 * @Date 2018/7/2 上午10:17
 */
public class JsonUtil {
	/**
	 * 将Object对象序列化成json字符串
	 * 默认日期格式为yyyy-MM-dd HH:mm:ss
	 * @param obj 待序列化对象
	 * @return json字符串
	 */
	public static String object2JsonString(Object obj){
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 将json格式的字符串反序列化为Object
	 * @param jsonString json字符串
	 * @param clazz 对象类型
	 * @return
	 */
	public static <T> T jsonString2Object(String jsonString,Class<T> clazz){
		return JSON.parseObject(jsonString, clazz);
	}

	/**
	 * 将json格式的字符串反序列化为List
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonString2List(String jsonString, Class<T> clazz) {
		return JSON.parseArray(jsonString, clazz);
	}

	/**
	 * 将json格式的字符串反序列化为Map
	 * Map的key为java.lang.String,value为java.lang.Object
	 * @param jsonString json字符串
	 * @return
	 */
	public static Map<String,Object> jsonString2Map(String jsonString){
		return JSON.parseObject(jsonString, new TypeReference<Map<String,Object>>(){});
	}
}
