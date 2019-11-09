package tzc.badminton.utils;

/**
 * 加密解密工具类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
public class CryptUtil {

    /**
     * 对object进行加密
     * @param object
     * @return
     */
    public static Object encrypt(Object object) {
        return encryptAlgorithm(object);
    }

    /**
     * 对object进行解密
     * @param object
     * @return
     */
    public static Object decrypt(Object object) {
        return decryptAlgorithm(object);
    }

    /**
     * todo 自己实现的加密算法
     * @param object
     * @return
     */
    private static Object encryptAlgorithm(Object object) {
        return object;
    }

    /**
     * todo 自己实现的解密算法
     * @param object
     * @return
     */
    private static Object decryptAlgorithm(Object object) {
        return object;
    }
}
