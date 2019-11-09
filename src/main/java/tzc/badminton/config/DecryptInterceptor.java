package tzc.badminton.config;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tzc.badminton.annotation.Encrypt;
import tzc.badminton.utils.ClassUtil;
import tzc.badminton.utils.CryptUtil;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * mybatis解密拦截器
 *      对数据库实体类中由@Encrypt修饰的字段进行解密(select)
 *      依据：handleResultSets
 * @author Planeswalker23
 * @date Created in 2019-11-09
 */

@Component
@Intercepts({
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = Statement.class)
        })
public class DecryptInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(DecryptInterceptor.class);
    /**
     * 类别名
     */
    private static String DECRYPT_ALIASES = "[解密拦截器]";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (ObjectUtils.isEmpty(result)){
            return null;
        }
        if (result instanceof ArrayList) {
            // 将结果集对象转换成集合对象，循环对集合中每个对象调用解密方法
            List<Object> resultList = Collections.singletonList(result);
            if (!CollectionUtils.isEmpty(resultList)){
                resultList.forEach(this::decryptPerObject);
            }
        } else {
            // 单结果集解密
            this.decryptPerObject(result);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 对被{@link Encrypt}注解修饰的属性进行解密
     * @param result 结果集单个对象
     */
    private void decryptPerObject(Object result) {
        // 根据类及注解类获取类中的使用注解修饰的属性列表
        List<Field> fieldsAnnotatedByEncrypt =
                ClassUtil.getAnnotationFieldsByAnnotation(result.getClass(), Encrypt.class);
        fieldsAnnotatedByEncrypt.forEach(field -> {
            Object value = ClassUtil.getValueByFieldName(result, field.getName());
            if (ObjectUtils.isEmpty(value)) {
                return;
            }
            // 使用解密算法对该字段进行解密
            Object newValue = CryptUtil.decrypt(value);
            ClassUtil.setValueByFieldName(result, field.getName(), newValue);
            logger.info("\t{}解密成功，[{}]对象解密前的[{}]属性值为: [{}]，解密后的属性值为: [{}]",
                    DECRYPT_ALIASES, result.getClass().toString(), field.getName(), value, newValue);
        });
    }
}
