package com.aliyun.iotx.city.demos.app.freeaccess.login.web.controller;

import com.aliyun.iotx.city.demos.app.freeaccess.common.Constants;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录控制器
 * @Author 安悟
 * @Date 2018/6/29 下午3:32
 */
@Controller
@RequestMapping("/")
public class IndexController {

	Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	LoginService loginService;

    @Value("${app.logoutRedirectUrl}")
    private String logoutRedictUrl;

	/**
	 * 首页
	 * @return
	 */
	@GetMapping("index")
	public String index() {
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

    @PostMapping("login")
    public String login(String userName, String password) {
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_KEY_LOGIN_USER);
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("origin")) {
                    String value = cookie.getValue();
                    String requestType = request.getHeader("X-Requested-With");
                    if ("smart_city".equals(value) && !"XMLHttpRequest".equals(requestType)) {
                        try {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            response.sendRedirect(logoutRedictUrl);
                            LOGGER.info("redirect to city link login page...");
                        } catch (IOException e) {
                            LOGGER.error("", e);
                        }
                    }
                }
            }
        }

        //根据标识判断是跳转到前台首页还是后台首页
        LOGGER.info("redirect to system login page...");
        return "redirect:/login";
    }
}
