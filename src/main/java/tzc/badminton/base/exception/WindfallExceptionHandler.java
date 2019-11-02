package tzc.badminton.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @ExceptionHandler(value = LoginException.class)
    public String loginExceptionHandler(LoginException e) {
        // 登录自定义异常
        log.warn(LOGIN_SERVICE + e.getClass() + e.getMessage());
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉登录业务性自定义异常 WindfallException.class
     * @param e 业务性自定义异常
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @ExceptionHandler(value = WindfallException.class)
    public String windfallExceptionHandler(WindfallException e) {
        // 业务性自定义异常
        log.warn(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉参数校验异常 MethodArgumentNotValidException.class
     * @param e 参数校验异常
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 参数校验异常
        log.warn(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉所有异常 Exception.class
     * @param e 所有异常
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(Exception e) {
        // 其他未知异常
        log.error(e.getMessage(), e);
        return Response.failed(Constant.SYSTEM_ERROR);
    }

}
