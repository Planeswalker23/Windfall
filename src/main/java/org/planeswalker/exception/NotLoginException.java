package org.planeswalker.exception;

import org.planeswalker.base.LoginErrors;

/**
 * 未登录异常
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
public class NotLoginException extends LoginException {

    public NotLoginException() {
        super(LoginErrors.USER_NOT_LOGIN);
    }
}
