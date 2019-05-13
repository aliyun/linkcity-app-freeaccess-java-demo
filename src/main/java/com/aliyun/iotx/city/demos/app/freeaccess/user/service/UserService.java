package com.aliyun.iotx.city.demos.app.freeaccess.user.service;

import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.city.demos.app.freeaccess.common.config.AppEnvParam;
import com.aliyun.iotx.city.demos.app.freeaccess.common.util.RandomUtil;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 安悟
 * @Date 2018/6/30 下午3:50
 */
@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	AppEnvParam environment;
	@Autowired
    SyncApiClient syncClient;

	private Map<String, User> users = null;

	@PostConstruct
	private void init() {
		users = new ConcurrentHashMap<>();
        User user = new User();
        user.setUid(RandomUtil.uuidString());
        user.setName("zhangsan");
        user.setPassword("123");
        user.setAge(20);
        user.setEmail("zhangsan@test.com");
        user.setBirthday(new Date());
        user.setCreateTime(new Date());
        user.setLastupdate(new Date());
		users.put(user.getUid(), user);
	}

	/**
	 * 添加用户
	 * @param user 用户
	 * @return
	 */
	public boolean add(User user) {
		user.setUid(RandomUtil.uuidString());
		return users.put(user.getUid(), user) != null;
	}

	/**
	 * 根据id获取用户
	 * @param userId 用户id
	 * @return 用户信息
	 */
	public User get(String userId) {
		return users.get(userId);
	}

	/**
	 * 更新用户
	 * @param user 用户信息
	 * @return
	 */
	public boolean update(User user) {
		if (users.containsKey(user.getUid())) {
            User existUser = users.get(user.getUid());
            existUser.setLastupdate(new Date());
            existUser.setBirthday(user.getBirthday());
            existUser.setEmail(user.getEmail());
            existUser.setAge(user.getAge());
            existUser.setName(user.getName());
            existUser.setPassword(user.getPassword());
			return true;
		}
		return false;
	}

	/**
	 * 删除用户
	 * @param uid 用户id
	 * @return
	 */
	public boolean delete(String uid) {
		if (users.containsKey(uid)) {
			users.remove(uid);
			return true;
		}

		return false;
	}

	public User findByUserName(String username) {
		return users.values().stream()
				.filter(it -> it.getName().equals(username))
				.findFirst()
				.orElseGet(() -> null);
	}

	public List<User> list() {
		List<User> list = new ArrayList<>();
		list.addAll(users.values());
		return list;
	}
}
