package tzc.badminton.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tzc.badminton.base.Constant;
import tzc.badminton.base.Response;

import static tzc.badminton.base.Constant.LOGIN_SERVICE;

/**
 * 全局异常处理
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@RestControllerAdvice
public class WindfallExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(WindfallExceptionHandler.class);

    /**
     * 拦截捕捉登录自定义异常 LoginException.class
     * @param e 登录自定义异常
     * @return {@link tzc.badminton.base.Response}
     */
    @ExceptionHandler(value = LoginException.class)
    public Response loginExceptionHandler(Exception e) {
        // 登录自定义异常
        log.error(LOGIN_SERVICE + e.getClass() + e.getMessage());
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉登录业务性自定义异常 Exception.class
     * @param e 业务性自定义异常
     * @return {@link tzc.badminton.base.Response}
     */
    @ExceptionHandler(value = WindfallException.class)
    public Response windfallExceptionHandler(Exception e) {
        // 业务性自定义异常
        log.error(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉所有异常 Exception.class
     * @param e 所有异常
     * @return {@link tzc.badminton.base.Response}
     */
    @ExceptionHandler(value = Exception.class)
    public Response allExceptionHandler(Exception e) {
        // 其他未知异常
        log.error(e.getMessage(), e);
        return Response.failed(Constant.SYSTEM_ERROR);
    }

}
