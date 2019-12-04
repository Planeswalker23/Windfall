package org.planeswalker.exception;

/**
 * 未登录异常
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
public class NotLoginException extends RuntimeException {

    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }
}
