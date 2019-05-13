package com.aliyun.iotx.city.demos.app.freeaccess.user.bean;

/**
 * 用户角色关联关系
 * @Author 安悟
 * @Date 2018/8/16 下午1:17
 */
public class UserRole {
    private String uid;
    private String rid;

    public UserRole() {

    }

    public UserRole(String uid, String rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "uid='" + uid + '\'' +
                ", rid='" + rid + '\'' +
                '}';
    }
}
