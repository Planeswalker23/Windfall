package tzc.badminton.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    private static Logger logger = LoggerFactory.getLogger(WindfallExceptionHandler.class);

    /**
     * 拦截捕捉登录自定义异常 LoginException.class
     * @param e 登录自定义异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler(value = LoginException.class)
    public String loginExceptionHandler(LoginException e) {
        // 登录自定义异常
        logger.warn("{}: {}",LOGIN_SERVICE, e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉业务性自定义异常 WindfallException.class
     * @param e 业务性自定义异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler(value = WindfallException.class)
    public String windfallExceptionHandler(WindfallException e) {
        // 业务性自定义异常
        logger.warn(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉参数校验异常 MethodArgumentNotValidException.class
     * @param e 参数校验异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Response.failed(this.getValidErrorMessage(e));
    }

    /**
     * 拦截捕捉参数校验异常 BindException.class
     * 若与MethodArgumentNotValidException异常一起处理，会产生直接抛出BindException的错误返回
     * @param e 参数校验异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler({BindException.class})
    public String bindExceptionHandler(BindException e) {
        return Response.failed(this.getValidErrorMessage(e));
    }

    /**
     * 从参数校验异常中获取校验失败信息
     * @param e BindException or MethodArgumentNotValidException
     * @return String
     */
    private String getValidErrorMessage(Exception e) {
        // 获取校验失败信息
        BindingResult bindingResult;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            logger.warn(e.getMessage(), e);
            return null;
        }
        // 拼接校验异常信息
        StringBuilder errorMessage = new StringBuilder(Constant.VALID_ERROR).append(Constant.MAO_HAO);
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(Constant.DOU_HAO);
        }
        // 参数校验异常
        logger.warn(errorMessage.toString(), e);
        return errorMessage.toString();
    }

    /**
     * 拦截捕捉所有异常 Exception.class
     * @param e 其他所有未定义的异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(Exception e) {
        // 其他未知异常
        logger.error(e.getMessage(), e);
        return Response.failed(Constant.SYSTEM_ERROR);
    }

    /**
     * 拦截捕捉用户未登录异常 NotLoginException.class
     * @param e 用户未登录异常
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @ExceptionHandler(value = NotLoginException.class)
    public String notLoginExceptionHandler(NotLoginException e) {
        // 用户未登录异常
        logger.warn("{}: {}", Constant.USER_NOT_LOGIN, e.getMessage(), e);
        return Response.failed(Constant.USER_NOT_LOGIN);
    }
}
