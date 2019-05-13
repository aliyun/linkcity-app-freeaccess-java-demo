package com.aliyun.iotx.city.demos.app.freeaccess.user.service;

import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.city.demos.app.freeaccess.common.Constants;
import com.aliyun.iotx.city.demos.app.freeaccess.common.config.AppEnvParam;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.RandomUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author 安悟
 * @Date 2018/7/3 下午4:56
 */
@Service
public class RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	AppEnvParam environment;
	@Autowired
    SyncApiClient syncClient;

	/**
	 * 角色信息
	 */
	private Map<String, Role> roles = null;
	/**
	 * 用户角色信息
	 */
	private Map<String, Set<String>> userRoles;

	@PostConstruct
	private void init() {
		roles = new ConcurrentHashMap<>();

        Role cityAdminRole = new Role();
        cityAdminRole.setRid(RandomUtil.uuidString());
        cityAdminRole.setCode(Constants.ROLE_CITY_ADMIN_CODE);
        cityAdminRole.setName("管理员角色");
        cityAdminRole.setCreateTime(new Date());
        cityAdminRole.setLastupdate(new Date());
		roles.put(cityAdminRole.getRid(), cityAdminRole);

        Role cityNormalRole = new Role();
        cityNormalRole.setRid(RandomUtil.uuidString());
        cityNormalRole.setCode(Constants.ROLE_CITY_NORMAL_CODE);
        cityNormalRole.setName("普通角色");
        cityNormalRole.setCreateTime(new Date());
        cityNormalRole.setLastupdate(new Date());
        roles.put(cityNormalRole.getRid(), cityNormalRole);

		userRoles = new ConcurrentHashMap<>();
	}

	/**
	 * 添加角色
	 * @param role 角色
	 * @return
	 */
	public boolean add(Role role) {
		role.setRid(RandomUtil.uuidString());
		return roles.putIfAbsent(role.getRid(), role) == null;
	}

	/**
	 * 根据id获取角色
	 * @param rid 角色id
	 * @return 角色信息
	 */
	public Role get(String rid) {
		return roles.getOrDefault(rid, null);
	}

	/**
	 * 更新角色
	 * @param role 角色信息
	 * @return
	 */
	public boolean update(Role role) {
		if (roles.containsKey(role.getRid())) {
            Role existRole = roles.get(role.getRid());
            existRole.setLastupdate(new Date());
            existRole.setName(role.getName());
			return true;
		}

		return false;
	}

	/**
	 * 删除角色
	 * @param rid 角色id
	 * @return
	 */
	public boolean delete(String rid) {
		if (roles.containsKey(rid)) {
			roles.remove(rid);
			return true;
		}

		return false;
	}

	public List<Role> list(List<String> roleCodes) {
        if (CollectionUtils.isEmpty(roleCodes)) {
            List<Role> list = new ArrayList<>();
            list.addAll(roles.values());
            return list;
        }
		return roles.values()
                .stream()
                .filter(it -> roleCodes.contains(it.getCode()))
                .collect(Collectors.toList());
	}

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
	public Set<Role> getRolesByUserId(String userId) {
		if (userRoles.containsKey(userId)) {
			return userRoles.get(userId)
					.stream()
					.map(it -> get(it))
					.collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}

    /**
     * 将角色授权给用户
     * @param userId
     * @param roleIds
     * @return
     */
	public boolean authRole2User(String userId, List<String> roleIds) {
        if (!CollectionUtils.isEmpty(roleIds)) {
			if (userRoles.containsKey(userId)) {
				userRoles.get(userId)
						.addAll(roleIds);
			} else {
				Set<String> set = new HashSet<>();
				set.addAll(roleIds);
				userRoles.put(userId, set);
			}
			return true;
        }
		return false;
	}

    /**
     * 将角色回收
     * @param userId
     * @param roleIds
     * @return
     */
    public boolean revokeRoleFromUser(String userId, List<String> roleIds) {
        if (!CollectionUtils.isEmpty(roleIds)) {
            if (userRoles.containsKey(userId)) {
				Set<String> set = userRoles.get(userId);
				set.removeAll(roleIds);
				return true;
			}
        }
        return false;
    }
}
