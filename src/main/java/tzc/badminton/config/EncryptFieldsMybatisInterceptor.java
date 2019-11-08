package tzc.badminton.config;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tzc.badminton.annotation.Encrypt;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * mybatis拦截器
 *      对数据库实体类中由@Encrypt修饰的字段进行加密(insert、update、where参数)
 *      依据：对setParameters方法进行拦截
 * @author Planeswalker23
 * @date Created in 2019-11-08
 */

@Intercepts({
        @Signature(type = ParameterHandler.class,
                method = "setParameters",
                args = PreparedStatement.class)})
@Component
public class EncryptFieldsMybatisInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(EncryptFieldsMybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("==================加密拦截器: start working==================");
        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
        if (invocation.getTarget() instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];

            // 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
            Field boundSqlField = parameterHandler.getClass().getDeclaredField("boundSql");
            boundSqlField.setAccessible(true);
            BoundSql boundSql = (BoundSql) boundSqlField.get(parameterHandler);
            // 反射获取 参数对像
            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);

            // 获取类对象
            Class clazz = parameterObject.getClass();
            List<Field> Fields = Arrays.asList(clazz.getFields());
            Fields.forEach(field -> {
                // 获取属性上的@Encrypt注解
                Annotation annotation = field.getAnnotation(Encrypt.class);
                if (annotation != null) {
                    // 进行加密

                }
            });



            // 改写的参数设置到原parameterHandler对象
            parameterField.set(parameterHandler, parameterObject);
            parameterHandler.setParameters(ps);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
