package tzc.badminton.base;

import com.alibaba.fastjson.JSON;

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
        return JSON.toJSONString(this);
    }

    public static String success() {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        return res.toString();
    }

    public static String success(Integer num) {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        return res.toString();
    }

    public static <T> String success(T data) {
        Response<T> res = new Response<T>();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        res.setData(data);
        return res.toString();
    }

    public static String failed(String reason) {
        Response res = new Response();
        res.setSuccess(false);
        res.setMessage(reason);
        return res.toString();
    }
}
