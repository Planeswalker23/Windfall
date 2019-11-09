package tzc.badminton.config;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tzc.badminton.annotation.Encrypt;
import tzc.badminton.utils.ClassUtil;
import tzc.badminton.utils.CryptUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

/**
 * mybatis拦截器
 *      对数据库实体类中由@Encrypt修饰的字段进行加密(insert、update、where参数)
 *      依据：对setParameters方法进行拦截
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */

@Component
@Intercepts({
        @Signature(
                type = ParameterHandler.class,
                method = "setParameters",
                args = PreparedStatement.class)
        })
public class EncryptInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(EncryptInterceptor.class);
    /**
     * ParameterHandler对象中的属性
     */
    private static String PARAMETER_OBJECT = "parameterObject";
    /**
     * 加密拦截器别名
     */
    private static String ENCRYPT_ALIASES = "[加密拦截器 - setParameters时]";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截 ParameterHandler 的 setParameters 方法。动态设置参数，找到由@Encrypt注解修饰的属性，进行加密
        if (invocation.getTarget() instanceof ParameterHandler) {
            // Mybatis实现sql入参设置的对象
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            // 反射获取 DefaultParameterHandler 对象的 parameterObject 属性，然后获取
            Field parameterObjectField = parameterHandler.getClass().getDeclaredField(PARAMETER_OBJECT);
            parameterObjectField.setAccessible(true);
            // 此对象为为sql设置字段值的对象，即parameterType中声明的对象
            Object paramObject = parameterObjectField.get(parameterHandler);
            // 根据类及注解类获取类中的使用注解修饰的属性列表
            List<Field> fieldsAnnotatedByEncrypt
                    = ClassUtil.getAnnotationFieldsByAnnotation(paramObject.getClass(), Encrypt.class);
            fieldsAnnotatedByEncrypt.forEach(field -> {
                Object value = ClassUtil.getValueByFieldName(paramObject, field.getName());
                if (ObjectUtils.isEmpty(value)) {
                    return;
                }
                // 使用加密算法(根据配置)对该字段进行加密
                Object newValue = CryptUtil.encrypt(value);
                ClassUtil.setValueByFieldName(paramObject, field.getName(), value);
                logger.info("\t{}加密成功，[{}]对象加密前的[{}]属性值为: [{}]，加密后的属性值为: [{}]",
                        ENCRYPT_ALIASES, paramObject.getClass().toString(), field.getName(), value, newValue);
            });
            // 改写的参数设置到原parameterHandler对象，未更新重新设置一遍不影响程序进行
            parameterObjectField.set(parameterHandler, paramObject);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
