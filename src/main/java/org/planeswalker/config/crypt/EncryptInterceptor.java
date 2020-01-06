package org.planeswalker.config.crypt;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * mybatis拦截器
 *      对数据库实体类中由@Encrypt修饰的字段进行加密(insert、update、where参数)
 *      依据：对setParameters方法进行拦截
 * @deprecated 对数据的修改尽量不要放在拦截器中，可能会coder查不出问题（不知道此拦截器的话）
 * @author Planeswalker23
 * @date Created in 2019-11-08
 * //@Component
 * //@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class)})
 */

@Deprecated
public class EncryptInterceptor implements Interceptor {

    /**
     * ParameterHandler对象中的属性
     */
    private static String PARAMETER_OBJECT = "parameterObject";
    @Autowired
    private CryptExecutor cryptExecutor;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截 ParameterHandler 的 setParameters 方法。动态设置参数，找到由@Encrypt注解修饰的属性，进行加密
        // Mybatis实现sql入参设置的对象
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        // 反射获取 DefaultParameterHandler 对象的 parameterObject 属性，然后获取
        Field parameterObjectField = parameterHandler.getClass().getDeclaredField(PARAMETER_OBJECT);
        parameterObjectField.setAccessible(true);
        // 此对象为为sql设置字段值的对象，即parameterType中声明的对象
        Object paramObject = parameterObjectField.get(parameterHandler);
        // paramObject分为list和单个实体对象执行
        if (paramObject instanceof List) {
            // 将paramObject对象使用list指代
            List list = (List) paramObject;
            if (!list.isEmpty()) {
                list.forEach((res)->cryptExecutor.cryptForSingleObject(res, EncryptStrategy.class));
            }
        } else {
            // 无须接收paramObject对象，因为此对象已经在方法中修改
            cryptExecutor.cryptForSingleObject(paramObject, EncryptStrategy.class);
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
