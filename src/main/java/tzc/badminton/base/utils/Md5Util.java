package tzc.badminton.base.utils;

import org.apache.tomcat.util.security.MD5Encoder;

/**
 * 密码加密工具类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class Md5Util {

    /**
     * md5加密，所有密码参数需使用此方法加密
     * @param password
     * @return
     */
    public static String md5(String password) {
        return MD5Encoder.encode(password.getBytes());
    }
}
