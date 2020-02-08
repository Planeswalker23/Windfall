package org.planeswalker.exception;

/**
 * 自定义异常类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class WindfallException extends RuntimeException {

    public WindfallException() {
    }

    public WindfallException(String message) {
        super(message);
    }
}
