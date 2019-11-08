package tzc.badminton.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 密码加密工具类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class NumberUtil {

    /**
     * md5加密，所有密码参数需使用此方法加密
     * @param password
     * @return
     */
    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    /**
     * 生成UUID
     * @return
     */
    public static String createUuId() {
        return UUID.randomUUID().toString();
    }
}
