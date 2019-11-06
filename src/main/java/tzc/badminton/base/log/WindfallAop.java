package tzc.badminton.base.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志aop
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */

@Aspect
@Component
public class WindfallAop {

    private static Logger logger = LoggerFactory.getLogger(WindfallAop.class);

    /**
     * 在接口前后输出日志aop
     * @param pjp
     * @param log
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(log)")
    public Object outPutLogBeforeAndAfterDoMethods(ProceedingJoinPoint pjp, Log log) throws Throwable {
        logger.info("——————————Start: {}", log.value());
        // 接口类型
        logger.info("连接点类型: {}", pjp.getKind());
        // 类名
        logger.info("目标对象: {}", pjp.getTarget());
        // 获取传入参数
        Object[] args = pjp.getArgs();
        logger.info("Args参数: ");
        // 遍历参数数组
        for (int i = 0; i < args.length; i++) {
            logger.info("\t==>input[{}]:\t{}", i, JSON.toJSONString(args[i]));
        }
        logger.info("方法签名: {}", pjp.getSignature());
        // 获取接口返回内容
        Object retVal = pjp.proceed();
        String retValStr = null;
        if (retVal instanceof String){
            retValStr = (String) retVal;
        } else if (retVal != null){
            retValStr = JSON.toJSONString(retVal);
        }
        if (retValStr != null) {
            logger.info("返回结果: {}", retValStr);
        }
        logger.info("——————————End: {}\n", log.value());
        return retVal;
    }
}
