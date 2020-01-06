package org.planeswalker.config.crypt;

import org.planeswalker.annotation.Encrypt;
import org.planeswalker.utils.ClassUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 加密解密策略执行器，通过工厂生成策略类，然后执行策略
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */
@Component
public class CryptExecutor {

    /**
     * 执行器持有的动作类，具体的执行策略
     */
    private CryptStrategy cryptStrategy;

    /**
     * 根据指定的策略类对指定的对象target进行解码、加密操作
     * @param target
     * @param clazz 必须实现了 CryptStrategy 接口
     * @return Object 此对象可以不接收，因为对象已经被修改
     */
    public Object cryptForSingleObject(Object target, Class<? extends CryptStrategy> clazz) {
        // 根据cryptType从工厂中生成具体的策略类
        cryptStrategy = CryptFactory.build(clazz);
        // 根据类及注解类获取类中的使用注解修饰的属性列表
        List<Field> fieldsAnnotatedByEncrypt = ClassUtil.getAnnotationFieldsByAnnotation(target.getClass(), Encrypt.class);
        fieldsAnnotatedByEncrypt.forEach(field -> {
            Object value = ClassUtil.getValueByFieldName(target, field.getName());
            if (ObjectUtils.isEmpty(value)) {
                return;
            }
            // 具体的执行策略
            cryptStrategy.run(target, value, field.getName());
        });
        return target;
    }
}
