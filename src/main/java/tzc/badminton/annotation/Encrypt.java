package tzc.badminton.annotation;

import java.lang.annotation.*;

/**
 * 加密解密注解
 * 使用此注解修饰的数据库实体类属性，在insert或update或用作where条件到数据库中时会被加密
 *                             在select出来只有会被解密
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

    // todo 加密规则、是否使用key等参数
}
