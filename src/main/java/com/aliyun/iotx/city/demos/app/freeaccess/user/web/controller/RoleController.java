package com.aliyun.iotx.city.demos.app.freeaccess.user.web.controller;

import com.aliyun.iotx.city.demos.app.freeaccess.common.util.JsonUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.Role;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 权限控制器
 * @Author 安悟
 * @Date 2018/7/3 下午4:53
 */
@Controller
@RequestMapping("/role/")
public class RoleController {
	@Autowired
	RoleService roleService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("list")
	public String list() {
		return "role/roleList";
	}

	@PostMapping("list")
	public String listData(String conditionJson, Model model) {
		List<Role> roles = roleService.list(null);
		System.out.println(JsonUtil.object2JsonString(roles));
		model.addAttribute("roles", roles);
		return "role/roleListData";
	}

	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@PostMapping("add")
	@ResponseBody
	public boolean add(Role role) {
		Date now = new Date();
		role.setCreateTime(now);
		role.setLastupdate(now);
		return roleService.add(role);
	}

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	@PostMapping("update")
	@ResponseBody
	public boolean update(Role role) {
		Date now = new Date();
		role.setLastupdate(now);
		return roleService.update(role);
	}

	/**
	 * 获取角色详情
	 * @param rid
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody
	public Role get(String rid) {
		return roleService.get(rid);
	}

	/**
	 * 删除角色
	 * @param rid
	 * @return
	 */
	@GetMapping("del")
	@ResponseBody
	public boolean delete(String rid) {
		return roleService.delete(rid);
	}
}
