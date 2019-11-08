package tzc.badminton.base;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tzc.badminton.utils.JacksonUtil;

/**
 * 返回公用类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Data
public class Response<T> {

    private static Logger logger = LoggerFactory.getLogger(Response.class);

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
        return JacksonUtil.toJSON(this);
    }

    public static String success() {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        logger.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static String success(Integer num) {
        Response res = new Response();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        logger.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static <T> String success(T data) {
        Response<T> res = new Response<T>();
        res.setSuccess(true);
        res.setMessage(Constant.SUCCESS);
        res.setData(data);
        logger.info("接口返回内容: {}", res.toString());
        return res.toString();
    }

    public static String failed(String reason) {
        Response res = new Response();
        res.setSuccess(false);
        res.setMessage(reason);
        logger.info("接口返回内容: {}", res.toString());
        return res.toString();
    }
}
