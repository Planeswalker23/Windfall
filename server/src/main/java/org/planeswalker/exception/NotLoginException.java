package org.planeswalker.exception;

import org.planeswalker.base.Errors;

/**
 * 未登录异常
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
public class NotLoginException extends RuntimeException {

    public NotLoginException() {
        super(Errors.USER_NOT_LOGIN);
    }
}
