package com.aliyun.iotx.city.demos.app.freeaccess.common.util;

import java.util.UUID;

/**
 * @Author 安悟
 * @Date 2018/8/13 下午10:40
 */
public class RandomUtil {

    /**
     *
     * @return
     */
    public static String uuidString() {
        return UUID.randomUUID().toString();
    }
}
