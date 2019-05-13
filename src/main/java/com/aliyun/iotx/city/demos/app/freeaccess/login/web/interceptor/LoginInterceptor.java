package com.aliyun.iotx.city.demos.app.freeaccess.login.web.interceptor;

import com.aliyun.iotx.city.demos.app.freeaccess.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author 安悟
 * @Date 2019/5/2 下午5:28
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${app.logoutRedirectUrl}")
    private String logoutRedictUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUserObj = session.getAttribute(Constants.SESSION_KEY_LOGIN_USER);
        if (null != loginUserObj) {
            return true;
        }
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
                            return false;
                        } catch (IOException e) {
                            LOGGER.error("", e);
                        }
                    }
                }
            }
        }

        //根据标识判断是跳转到前台首页还是后台首页
        LOGGER.info("redirect to system login page...");
        response.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
