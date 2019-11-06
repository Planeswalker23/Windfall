package tzc.badminton.base.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tzc.badminton.base.Constant;
import tzc.badminton.base.Response;
import tzc.badminton.base.utils.SessionUtil;
import tzc.badminton.module.entity.User;

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

    /**
     * 使用Login注解需要做的处理
     * 功能点:1、验证用户是否登录
     *       2、验证是否操作本人数据
     * @param pjp 连接点
     * @param @Login 使用对应注解(@Login)的方法才进行验证 {@link tzc.badminton.base.log.Login}
     * @return 通用格式返回
     * @throws Throwable proceed方法抛出
     */
    @Around("@annotation(tzc.badminton.base.log.Login)")
    public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        // 若session中没有userBean变量，即未登录，则返回用户未登录
        User userBean = SessionUtil.getUserBean();
        if (userBean == null) {
            return Response.failed(Constant.USER_NOT_LOGIN);
        }
        // 若已登录，放行，继续执行该方法
        return pjp.proceed(args);
    }
}
