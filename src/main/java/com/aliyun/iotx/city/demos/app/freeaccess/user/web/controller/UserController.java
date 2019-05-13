package com.aliyun.iotx.city.demos.app.freeaccess.user.web.controller;

import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.Role;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.User;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.RoleService;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 用户控制器
 * @Author 安悟
 * @Date 2018/7/2 上午10:59
 */
@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserService userService;
    @Autowired
	RoleService roleService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("list")
	public String list() {
		return "user/userList";
	}

	@PostMapping("list")
	public String listData(String conditionJson, Model model) {
		List<User> users = userService.list();
		model.addAttribute("users", users);
		return "user/userListData";
	}

	@GetMapping("listAll")
	@ResponseBody
	public List<User> listAllData() {
		List<User> users = userService.list();
		return users;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping("add")
	@ResponseBody
	public boolean add(User user) {
		Date now = new Date();
		user.setCreateTime(now);
		user.setLastupdate(now);
		return userService.add(user);
	}

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@PostMapping("update")
	@ResponseBody
	public boolean update(User user) {
		Date now = new Date();
		user.setLastupdate(now);
		return userService.update(user);
	}

	/**
	 * 获取用户详情
	 * @param uid
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody
	public User get(String uid) {
		return userService.get(uid);
	}

	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	@GetMapping("del")
	@ResponseBody
	public boolean delete(String uid) {
		return userService.delete(uid);
	}

    /**
     * 查询用户角色
     * @param uid
     * @return
     */
	@GetMapping("/roles")
    @ResponseBody
	public Map<String, Object> getUserRoles(String uid) {
        List<Role> allRolelist = roleService.list(null);
        Set<Role> rolesByUserId = roleService.getRolesByUserId(uid);
        Map<String, Object> roles = new HashMap<>(3);
        roles.put("all", allRolelist);
        roles.put("authed", rolesByUserId);

        return roles;
    }

    @PostMapping("/auth")
    @ResponseBody
    public boolean authRole2User(String uid, String rid) {
        roleService.authRole2User(uid, Arrays.asList(rid));
        return true;
    }

    @PostMapping("/revoke")
	@ResponseBody
    public boolean revokeRoleFromUser(String uid, String rid) {
        roleService.revokeRoleFromUser(uid, Arrays.asList(rid));
        return true;
    }
}
