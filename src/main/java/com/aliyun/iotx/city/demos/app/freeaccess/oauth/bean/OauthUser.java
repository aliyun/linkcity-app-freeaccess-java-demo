package com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean;

/**
 * @Author 安悟
 * @Date 2018/8/13 下午9:51
 */
public class OauthUser {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 该用户角色
     */
    private String accessRoleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessRoleId() {
        return accessRoleId;
    }

    public void setAccessRoleId(String accessRoleId) {
        this.accessRoleId = accessRoleId;
    }

    @Override
    public String toString() {
        return "OauthUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", accessRoleId='" + accessRoleId + '\'' +
                '}';
    }
}
