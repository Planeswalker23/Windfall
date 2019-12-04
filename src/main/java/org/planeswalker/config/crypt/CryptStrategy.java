package org.planeswalker.config.crypt;

/**
 * 加密解密 策略接口类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
public interface CryptStrategy {

    /**
     * 执行具体的策略方法
     * @param target
     * @param oldValue
     * @param fieldName
     */
    void run(Object target, Object oldValue, String fieldName);
}
