package tzc.badminton.base.log;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface Log {

    /**
     * 接口描述
     * @return
     */
    String value() default  "";
}
