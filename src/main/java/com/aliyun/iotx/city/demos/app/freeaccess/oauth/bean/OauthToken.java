package com.aliyun.iotx.city.demos.app.freeaccess.oauth.bean;

/**
 * @Author 安悟
 * @Date 2018/8/13 下午9:35
 */
public class OauthToken {
    /**
     * 用户授权令牌
     */
    private String accessToken;
    /**
     * 授权令牌有效期,单位秒
     */
    private Long expiredTime;
    /**
     * 刷新令牌,当授权令牌过期时,可以刷新access_token,如果有权限则返回
     */
    private String refreshToken;
    /**
     * 令牌类型(默认值为Bearer)
     */
    private String tokenType;
    /**
     * Linkcity的账户id
     */
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
