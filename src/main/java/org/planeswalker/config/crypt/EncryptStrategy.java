package org.planeswalker.config.crypt;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.utils.ClassUtil;
import org.planeswalker.utils.CryptUtil;

/**
 * 加密具体策略类
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
@Slf4j
public class EncryptStrategy implements CryptStrategy {

    /**
     * 执行具体的策略方法
     * @param target
     * @param oldValue
     * @param fieldName
     */
    @Override
    public void run(Object target, Object oldValue, String fieldName) {
        // 使用加密算法进行解密
        Object newValue = CryptUtil.encrypt(oldValue);
        // 重新赋值
        ClassUtil.setValueByFieldName(target, fieldName, newValue);
        log.info("\t加密成功，加密前的[{}]属性值为: [{}]，加密后的属性值为: [{}]", fieldName, oldValue, newValue);
    }
}
