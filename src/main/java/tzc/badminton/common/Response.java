package tzc.badminton.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 返回公用类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        //返回json时只返回不为null的字段
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }

    public static Response success() {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        return res;
    }

    public static Response success(String message) {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(message);
        return res;
    }

    public static Response success(Integer num) {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        return res;
    }

    public static <T> Response success(T data) {
        Response<T> res = new Response<T>();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        res.setData(data);
        return res;
    }

    public static Response failed(String reason) {
        Response res = new Response();
        res.setSuccess(false);
        res.setMessage(reason);
        return res;
    }
}
