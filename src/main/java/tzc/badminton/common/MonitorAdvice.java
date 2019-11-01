package tzc.badminton.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static tzc.badminton.common.Constant.LOGIN_SERVICE;

/**
 * 全局异常处理
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@RestControllerAdvice
public class MonitorAdvice {

    private static Logger log = LoggerFactory.getLogger(MonitorAdvice.class);

    /**
     * 拦截捕捉异常 Exception.class
     * @param e 所有异常
     * @return java.lang.String
     */
    @ExceptionHandler(value = Exception.class)
    public Response monitorErrorHandler(Exception e) {
        if (e instanceof LoginException) {
            // 登录自定义异常
            log.error(LOGIN_SERVICE + e.getClass() + e.getMessage());
            return Response.failed(e.getMessage());
        } else if (e instanceof WindfallException) {
            // 业务性自定义异常
            log.error(e.getMessage(), e);
            return Response.failed(e.getMessage());
        } else {
            // 其他未知异常
            log.error(e.getMessage(), e);
            return Response.failed(Constant.SYSTEM_ERROR);
        }
    }

}
