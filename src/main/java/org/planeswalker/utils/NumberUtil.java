package org.planeswalker.utils;

import java.util.UUID;

/**
 * 操作数字工具类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class NumberUtil {

    /**
     * 生成UUID
     * @return
     */
    public static String createUuId() {
        return UUID.randomUUID().toString();
    }
}
