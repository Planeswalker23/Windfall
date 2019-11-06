package tzc.badminton.base.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证登录注解
 * @description 添加此注解的方法，必须要登录后才能访问接口
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Login {
}
