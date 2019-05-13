package com.aliyun.iotx.city.demos.app.freeaccess.login.web.controller;

import com.aliyun.iotx.city.demos.app.freeaccess.common.Constants;
import com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean.OauthToken;
import com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean.OauthUser;
import com.aliyun.iotx.city.demos.app.freeaccess.oauth.service.OauthLoginService;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.Role;
import com.aliyun.iotx.city.demos.app.freeaccess.user.bean.User;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.RoleService;
import com.aliyun.iotx.city.demos.app.freeaccess.user.service.UserService;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.Subject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用集成登录
 * @Author 安悟
 * @Date 2018/8/13 下午6:46
 */
@Controller
@RequestMapping("/thirdpart")
public class ThirdPartLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartLoginController.class);

    @Value("${app.redirectUrlTemplate}")
    private String redirectUrlTemplate;
    @Value("${app.logoutRedirectUrl}")
    private String logoutRedictUrl;

    @Autowired
    private OauthLoginService oauthLoginService;

    @Value("${app.id}")
    private String clientId;
    @Value("${app.grantType:authorization_code}")
    private String grantType;
    @Value("${app.role.normal}")
    private String normalRoles;
    @Value("${app.role.admin}")
    private String adminRoles;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static final Map<String, Subject> SUBJECT_MAP = new ConcurrentHashMap<>();
    private static final Map<String, Serializable> SESSION_ID_MAP = new ConcurrentHashMap<>();

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (null != session.getAttribute(Constants.SESSION_KEY_LOGIN_USER)) {
            LOGGER.info("already login...");
            return new ModelAndView(new RedirectView("/index"));
        } else {
            LOGGER.info("haven't login...");

            String redictUrl = getRedirectUrl(request);
            LOGGER.info("redictUrl = {}", redictUrl);
            String url = MessageFormat.format(redirectUrlTemplate, clientId, "abc", redictUrl);
            LOGGER.info(url);
            return new ModelAndView(new RedirectView(url));
        }
    }

    @RequestMapping("callback")
    public String callback(String code, String state, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("code = {}", code);

        if (StringUtils.hasText(code)) {
            // 根据code获取token
            String redictUrl = getRedirectUrl(request);
            OauthToken oauthToken = oauthLoginService.getOauthToken(code, grantType, redictUrl, clientId);
            if (null != oauthToken) {
                // 获取用户信息
                OauthUser authUser = oauthLoginService.getAuthUser(oauthToken.getAccessToken(), oauthToken.getUserId(), clientId);

                User findedUser = findUser(authUser);

                // 登录
                try {
                    // 动态授予角色
                    String accessRoleId = authUser.getAccessRoleId();
                    LOGGER.info("accessRoleId = {}", accessRoleId);
                    if (StringUtils.hasText(accessRoleId)) {
                        if (Constants.ROLE_CITY_NORMAL_CODE.equals(accessRoleId)) {
                            Arrays.asList(normalRoles.split(","));
                        } else if (Constants.ROLE_CITY_ADMIN_CODE.equals(accessRoleId)) {
                            Arrays.asList(adminRoles.split(","));
                        }
                    }

                    HttpSession session = request.getSession();
                    session.setAttribute(Constants.SESSION_KEY_LOGIN_USER, findedUser);

                    Cookie cookie = new Cookie("origin", "smart_city");
                    cookie.setMaxAge(30 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);

                    return "redirect:/index";

                } catch(Exception e) {
                    LOGGER.error("", e);
                }
            }
        }

        return null;
    }

    @RequestMapping(value = "/callback/logout")
    @ResponseBody
    public Map<String, Object> logout(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        String clientId = (String)params.get("clientId");
        String userId = (String)params.get("userId");
        LOGGER.info("third part logout, clientId = {}, userId = {}...", clientId, userId);
        LOGGER.info("params = {}", params);
        HttpSession session = request.getSession();
        if (null != session.getAttribute(Constants.SESSION_KEY_LOGIN_USER)) {
            session.removeAttribute(Constants.SESSION_KEY_LOGIN_USER);
        }
        Map<String, Object> resp = new HashedMap();
        resp.put("message", "logout success");
        resp.put("code", 200);

        return resp;

       /* try {
            response.getWriter().write(JsonUtil.object2JsonString(resp));
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        return new ModelAndView(new RedirectView(logoutRedictUrl));*/
    }

    private User findUser(OauthUser authUser) {
        // TODO 最好不要用userName,会重复
        User findedUser = userService.findByUserName(authUser.getUserName());
        if (null == findedUser) {
            findedUser = createUser(authUser);
        } else {
            // 用户已登录,判断是否需要修改权限
            // 简单点,直接移除老的权限,重新授权
            Set<Role> rolesByUserId = roleService.getRolesByUserId(findedUser.getUid());
            if (!CollectionUtils.isEmpty(rolesByUserId)) {
                List<String> roleIds = new ArrayList<>(rolesByUserId.size());
                rolesByUserId.forEach((role) -> {
                    roleIds.add(role.getRid());
                });
                roleService.revokeRoleFromUser(findedUser.getUid(), roleIds);
            }
            // 重新授权
            authRole(authUser, findedUser);
        }
        return findedUser;
    }

    private String getRedirectUrl(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL.toString());
            sb.append(url.getProtocol())
                    .append("://")
                    .append(url.getHost())
                    .append(":")
                    .append(url.getPort())
                    .append("/thirdpart/callback");
        } catch (MalformedURLException e) {
            LOGGER.error("", e);
        }
        return sb.toString();
    }

    /**
     * 创建用户
     * 创建三方用户时授予角色,此种方式三方用户权限变更时无法感知,还是查询的老的角色
     * 所以不能在创建用户时授予角色
     * @param authUser
     * @return
     */
    private User createUser(OauthUser authUser) {
        User user = new User();
        user.setName(authUser.getUserName());
        user.setPassword(generatePassword());
        user.setEmail("");
        user.setBirthday(new Date());
        user.setCreateTime(new Date());
        user.setLastupdate(new Date());
        user.setAge(100);
        userService.add(user);

        authRole(authUser, user);

        return user;
    }

    private void authRole(OauthUser authUser, User user) {
        String accessRoleId = authUser.getAccessRoleId();
        // 绑定角色
        List<String> roleCodes = new ArrayList<>();
        if (StringUtils.hasText(accessRoleId)) {
            if (Constants.ROLE_CITY_NORMAL_CODE.equals(accessRoleId)) {
                roleCodes.addAll(Arrays.asList(normalRoles.split(",")));
            } else if (Constants.ROLE_CITY_ADMIN_CODE.equals(accessRoleId)) {
                roleCodes.addAll(Arrays.asList(adminRoles.split(",")));
            }

            List<Role> roleList = roleService.list(roleCodes);

            List<String> roleIds = new ArrayList<>(roleList.size());
            for (Role role : roleList) {
                roleIds.add(role.getRid());
            }

            if (!CollectionUtils.isEmpty(roleIds)) {
                roleService.authRole2User(user.getUid(), roleIds);
            }
        }
    }

    private String generatePassword() {
        return "passw0rd";
    }
}
