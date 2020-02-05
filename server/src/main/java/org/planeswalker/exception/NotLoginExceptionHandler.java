package org.planeswalker.exception;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.base.ServicesEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 返回首页
 * @author Planeswalker23
 * @date Created in 2020/2/5
 */
@Slf4j
@ControllerAdvice
public class NotLoginExceptionHandler {

    /**
     * 包括未登录异常 {@link NotLoginException}
     * @param e 登录自定义异常
     * @return {@link Response}
     */
    @ExceptionHandler(value = NotLoginException.class)
    public String notLoginExceptionHandler(NotLoginException e) {
        // 登录自定义异常
        log.warn("[{}]: {}", ServicesEnum.LoginService.getServiceName(), e.getMessage(), e);
        return "redirect:/";
    }
}
