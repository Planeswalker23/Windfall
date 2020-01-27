package org.planeswalker.exception;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.base.Response;
import org.planeswalker.base.ServicesEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Slf4j
@RestControllerAdvice
public class WindfallExceptionHandler {

    /**
     * 拦截捕捉登录自定义异常 {@link LoginException}
     * 包括未登录异常 {@link NotLoginException}
     * @param e 登录自定义异常
     * @return {@link Response}
     */
    @ExceptionHandler(value = {LoginException.class, NotLoginException.class})
    public Response loginExceptionHandler(LoginException e) {
        // 登录自定义异常
        log.warn("[{}]: {}", ServicesEnum.LoginService.getServiceName(), e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉业务性自定义异常 {@link WindfallException}
     * @param e 业务性自定义异常
     * @return {@link Response}
     */
    @ExceptionHandler(value = WindfallException.class)
    public Response windfallExceptionHandler(WindfallException e) {
        // 业务性自定义异常
        log.warn(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截捕捉参数校验异常 {@link MethodArgumentNotValidException}
     * @param e 参数校验异常
     * @return {@link Response}
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Response.failed(this.getValidErrorMessage(e));
    }

    /**
     * 拦截捕捉参数校验异常 {@link BindException}
     * 若与MethodArgumentNotValidException异常一起处理，会产生直接抛出BindException的错误返回
     * @param e 参数校验异常
     * @return {@link Response}
     */
    @ExceptionHandler({BindException.class})
    public Response bindExceptionHandler(BindException e) {
        return Response.failed(this.getValidErrorMessage(e));
    }

    /**
     * 拦截捕捉参数校验异常 {@link IllegalArgumentException}
     * @param e 参数校验异常
     * @return {@link Response}
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public Response illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.warn(e.getMessage(), e);
        return Response.failed(Errors.EMPTY_PARAMS);
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
            log.warn(e.getMessage(), e);
            return null;
        }
        // 拼接校验异常信息
        StringBuilder errorMessage = new StringBuilder(Errors.VALID_ERROR).append(Constant.MAO_HAO);
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(Constant.DOU_HAO);
        }
        // 参数校验异常
        log.warn(errorMessage.toString(), e);
        return errorMessage.toString();
    }

    /**
     * 拦截捕捉所有异常 {@link Exception}
     * @param e 其他所有未定义的异常
     * @return {@link Response}
     */
    @ExceptionHandler(value = Exception.class)
    public Response allExceptionHandler(Exception e) {
        // 其他未知异常
        log.error(e.getMessage(), e);
        return Response.failed(Errors.SYSTEM_ERROR);
    }

    /**
     * 请求方式异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        // 请求方式异常
        log.warn("{}: {}", Errors.WRONG_REQUEST_METHOD, e.getMessage(), e);
        return Response.failed(Errors.WRONG_REQUEST_METHOD + Constant.MAO_HAO + e.getMethod());
    }
}
