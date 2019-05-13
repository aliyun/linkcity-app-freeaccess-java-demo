package com.aliyun.iotx.city.demos.app.freeaccess.user.service;

import com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions.AccountNotExistException;
import com.aliyun.iotx.city.demos.app.freeaccess.common.exceptions.PasswordNotMatchException;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 安悟
 * @Date 2018/6/29 下午5:02
 */
@Service
public class LoginService {

    @Autowired
	private UserService userService;

	public boolean checkUsernameAndPassword(String username, String password) {
		return true;
	}

	public void login(User user) {
        User findedUser = userService.findByUserName(user.getName());
        if (null == findedUser) {
            throw new AccountNotExistException();
        }
        String findedUserPassword = findedUser.getPassword();
        if (!user.getPassword().equals(findedUserPassword)) {
            throw new PasswordNotMatchException();
        }
    }
}
