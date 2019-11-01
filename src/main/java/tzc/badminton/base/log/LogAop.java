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
public class LogAop {

    private static Logger logger = LoggerFactory.getLogger(LogAop.class);

    /**
     * 输出日志aop
     * @param pjp
     * @param log
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(log)")
    public Object outPutLogBeforeDoMethods(ProceedingJoinPoint pjp, Log log) throws Throwable {
        // 输出流StringBuffer线程安全
        StringBuffer outPutString = new StringBuffer();
        outPutString.append("日志信息:\t").append(log.value()).append("\n");
        // 接口类型
        outPutString.append("Kind接口:\t").append(pjp.getKind()).append("\n");
        // 类名
        outPutString.append("Target:\t").append(pjp.getTarget().toString()).append("\n");
        // 获取传入参数
        Object[] args = pjp.getArgs();
        outPutString.append("Args:").append("\n");
        // 存储传入参数
        StringBuffer inputParams = new StringBuffer();
        // 遍历参数数组
        for (int i = 0; i < args.length; i++) {
            outPutString.append("\t==>input[").append(i).append("]:\t").append(args[i] == null ? "" : args[i].toString()).append("\n");
            // 统计请求参数 防止查询后的结果过长 在业务执行前统计
            try {
                // 跳过Mock测试框架中Json解析MockRequest的步骤
                String typeName = args[i].getClass().getName();
                if (typeName.contains("Mock")) {
                    inputParams.append(args[i].toString() + ",");
                } else {
                    inputParams.append(JSON.toJSONString(args[i]) + ",");
                }
            } catch (Exception e) {
                try {
                    inputParams.append(args[i].toString() + ",");
                } catch (Exception e1) {
                    continue;
                }
                continue;
            }
        }
        outPutString.append("Signature:\t").append(pjp.getSignature()).append("\n");
        outPutString.append("SoruceLocation:\t").append(pjp.getSourceLocation()).append("\n");
        outPutString.append("StaticPart:\t").append(pjp.getStaticPart()).append("\n");
        Object retVal = pjp.proceed();
        String retValStr = null;
        if (retVal instanceof String){
            retValStr = (String) retVal;
        } else if (retVal != null){
            retValStr = JSON.toJSONString(new Object[]{retVal});
        }
        if (retValStr != null) {
            outPutString.append("Result:\t").append(retValStr).append("\n");
        }
        outPutString.append("----------分割线----------").append("\n");
        logger.info(outPutString.toString());
        return retVal;
    }
}
