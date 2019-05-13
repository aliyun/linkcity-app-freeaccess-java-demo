package com.aliyun.iotx.city.demos.app.freeaccess.device.web.controller;

import com.aliyun.iotx.city.demos.app.freeaccess.common.util.JsonUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.DeviceInfo;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.EventValue;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.ProductAbility;
import com.aliyun.iotx.city.demos.app.freeaccess.device.bean.PropertyValue;
import com.aliyun.iotx.city.demos.app.freeaccess.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备控制器
 * @Author 安悟
 * @Date 2018/7/3 下午6:02
 */
@Controller
@RequestMapping("/device/")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	/**
	 * 概览页面
	 * @return
	 */
	@GetMapping("overview")
	public String overview() {
		return "device/overview";
	}

	/**
	 * 统计信息数据
	 * @param productKey
	 * @return
	 */
	@GetMapping("statistic")
	@ResponseBody
	public Map<String, Integer> getStatisticInfo(String productKey) {
		Map<String, Integer> statisticMap = new HashMap<>(4);

		int onlineCount = deviceService.getAuthorizedDeviceCount(productKey, 1);
		statisticMap.put("onlineCount", onlineCount);

		int offlineCount = deviceService.getAuthorizedDeviceCount(productKey, 3);
		statisticMap.put("offlineCount", offlineCount);

		int unActiveCount = deviceService.getAuthorizedDeviceCount(productKey, 0);
		statisticMap.put("unActiveCount", unActiveCount);

		return statisticMap;
	}

	/**
	 * 设备列表
	 * @param productKey
	 * @param status
	 * @param model
	 * @return
	 */
	@GetMapping("list/{productKey}/{status}")
	public String list(@PathVariable("productKey") String productKey, @PathVariable("status") int status, Model model) {
		List<DeviceInfo> authorizedDeviceInfo = deviceService.getAuthorizedDeviceInfo(productKey, status);
		model.addAttribute("deviceInfos", authorizedDeviceInfo);

		return "device/deviceList";
	}

	/**
	 * 设备详情
	 * @param productKey
	 * @param deviceName
	 * @param model
	 * @return
	 */
	@GetMapping("detail/{productKey}/{deviceName}")
	public String detail(@PathVariable("productKey") String productKey, @PathVariable("deviceName") String deviceName, Model model) {
		DeviceInfo deviceInfo = deviceService.getDeviceInfo(productKey, deviceName);
		model.addAttribute("deviceInfo", deviceInfo);
		return "device/detail";
	}

	/**
	 * 跳转到监控页面
	 * @param productKey
	 * @param deviceName
	 * @param model
	 * @return
	 */
	@GetMapping("monitor/{productKey}/{deviceName}")
	public String monitor(@PathVariable("productKey") String productKey, @PathVariable("deviceName") String deviceName, Model model) {
		List<ProductAbility> productAbilities = deviceService.getProductAbility(productKey);
		if (!CollectionUtils.isEmpty(productAbilities)) {
			model.addAttribute("productAbilitiesJson", JsonUtil.object2JsonString(resolveProductAbility(productAbilities)));
			model.addAttribute("productAbilities", resolveProductAbility(productAbilities));
		}
		model.addAttribute("productKey", productKey);
		model.addAttribute("deviceName", deviceName);

		return "device/monitor";
	}

	/**
	 * 跳转到监控页面(消息推送)
	 * @param productKey
	 * @param deviceName
	 * @param model
	 * @return
	 */
	@GetMapping("monitor/push/{productKey}/{deviceName}/{iotId}")
	public String monitorPush(@PathVariable("productKey") String productKey, @PathVariable("deviceName") String deviceName,
							  @PathVariable("iotId") String iotId, Model model) {
		List<ProductAbility> productAbilities = deviceService.getProductAbility(productKey);
		if (!CollectionUtils.isEmpty(productAbilities)) {
			model.addAttribute("productAbilitiesJson", JsonUtil.object2JsonString(resolveProductAbility(productAbilities)));
			model.addAttribute("productAbilities", resolveProductAbility(productAbilities));
		}
		model.addAttribute("productKey", productKey);
		model.addAttribute("deviceName", deviceName);
		model.addAttribute("iotId", iotId);

		return "device/monitorPush";
	}

	/**
	 * 获取属性上送数据
	 * @param productKey
	 * @param deviceName
	 * @param propertyNames
	 * @return
	 */
	@GetMapping("data/property/get")
	@ResponseBody
	public List<PropertyValue> propertyDataGet(String productKey, String deviceName, String propertyNames) {
		return deviceService.getDevicePropertys(productKey, deviceName, JsonUtil.jsonString2List(propertyNames, String.class));
	}

	/**
	 * 设置属性
	 * @param productKey
	 * @param deviceName
	 * @param propertiesJson
	 * @return
	 */
	@PostMapping("data/property/set")
	@ResponseBody
	public boolean propertyDataSet(String productKey, String deviceName, String propertiesJson) {
		return deviceService.setDeviceProperty(productKey, deviceName, JsonUtil.jsonString2Map(propertiesJson));
	}

	@PostMapping("data/service/invoke")
	@ResponseBody
	public boolean serviceInvoke(String productKey, String deviceName, String serviceName, String args) {
		Map<String, Object> map;
		if (StringUtils.hasText(args)) {
			map = JsonUtil.jsonString2Map(args);
		} else {
			map = Collections.emptyMap();
		}
		return deviceService.serviceInvoke(productKey, deviceName, serviceName, map);
	}

	/**
	 * 事件最后一次记录
	 * @param productKey
	 * @param deviceName
	 * @param eventCode
	 * @return
	 */
	@GetMapping("data/event/get")
	@ResponseBody
	public EventValue getEventLastRecord(String productKey, String deviceName, String eventCode) {
		return deviceService.getDeviceEventLastRecord(productKey, deviceName, eventCode);
	}

	/**
	 * 事件历史记录
	 * @param productKey
	 * @param deviceName
	 * @param eventCode
	 * @param eventType
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("data/event/list")
	@ResponseBody
	public List<EventValue> batchQueryDeviceEvent(String productKey, String deviceName, String eventCode,
							String eventType, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(start));
		System.out.println(sdf.format(end));
		return deviceService.batchQueryDeviceEvent(productKey, deviceName, eventCode, eventType, start, end);
	}

	private Map<String, Map<String, ProductAbility>> resolveProductAbility(List<ProductAbility> productAbilities) {
		Map<String, Map<String, ProductAbility>> map = new HashMap<>();
		map.put("PROPERTY", new HashMap<>());
		map.put("SERVICE", new HashMap<>());
		map.put("EVENT", new HashMap<>());

		for (ProductAbility productAbility : productAbilities) {
			String abilityType = productAbility.getAbilityType();
			map.get(abilityType).put(productAbility.getFunId(), productAbility);
		}

		return map;
	}

}
