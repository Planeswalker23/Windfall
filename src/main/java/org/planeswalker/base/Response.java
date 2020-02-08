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
    private Integer code;

    /**
     * 错误信息
     */
    private String reason;

    /**
     * 返回数据
     */
    private T res;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public static Response success() {
        Response res = new Response();
        res.setCode(Constant.SUCCESS_CODE);
        res.setReason(Constant.SUCCESS);
        log.info("接口返回内容: {}", res.toString());
        return res;
    }

    public static <T> Response<T> success(T data) {
        Response<T> res = new Response<T>();
        res.setCode(Constant.SUCCESS_CODE);
        res.setReason(Constant.SUCCESS);
        res.setRes(data);
        log.info("接口返回内容: {}", res.toString());
        return res;
    }

    public static Response failed(String reason) {
        Response res = new Response();
        res.setCode(Constant.FAILED_CODE);
        res.setReason(reason);
        log.info("接口返回内容: {}", res.toString());
        return res;
    }
}
