package org.planeswalker.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.utils.JacksonUtil;

/**
 * 返回公用类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Data
@Slf4j
public class Response<T> {

    /**
     * 返回结果是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public static String success() {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        log.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static String success(Integer num) {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        log.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static <T> String success(T data) {
        Response<T> res = new Response<T>();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        res.setData(data);
        log.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static String failed(String reason) {
        Response res = new Response();
        res.setSuccess(false);
        res.setMessage(reason);
        log.info("接口返回内容: {}", res.toString());
        return res.toString();
    }
}
