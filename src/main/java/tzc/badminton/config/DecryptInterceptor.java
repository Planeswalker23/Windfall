package tzc.badminton.config;

import com.google.common.collect.Lists;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tzc.badminton.config.crypt.CryptExecutor;
import tzc.badminton.config.crypt.DecryptStrategy;

import java.sql.Statement;
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

    @Autowired
    private CryptExecutor cryptExecutor;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行sql方法，获取结果集
        Object result = invocation.proceed();
        if (ObjectUtils.isEmpty(result)){
            // 需要返回invocation.proceed()方法得到的结果集，否则会报NPE
            return result;
        }
        if (result instanceof List) {
            // 将paramObject对象使用list指代
            List list = (List) result;
            if (!list.isEmpty()) {
                // 使用guava优化效率，返回大量数据可能会影响效率
                result = Lists.transform(list, (res)->cryptExecutor.cryptForSingleObject(res, DecryptStrategy.class));
            }
        } else {
            // 无须接收方法返回对象，因为此对象已经在方法中修改
            cryptExecutor.cryptForSingleObject(result, DecryptStrategy.class);
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
}
