package org.planeswalker.exception;

/**
 * 登录服务自定义异常
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class CommentException extends RuntimeException {

    public CommentException() {
    }

    public CommentException(String message) {
        super(message);
    }
}
