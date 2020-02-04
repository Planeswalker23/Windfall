package org.planeswalker.base;

/**
 * 一般性异常
 * @author Planeswalker23
 * @date Created in 2019/12/4
 */
public interface Errors {

    String EDIT_FAILED = "未修改任何数据";

    String EMPTY_PARAMS = "参数为空";

    String WRONG_DATA = "数据错误，请联系管理员";

    String SYSTEM_ERROR = "系统错误，请联系系统管理员";

    String VALID_ERROR = "参数校验失败";

    String TO_JSON_FAILED = "解析JSON失败";

    String NOT_BELONG_TO_ANNOTATION = "不属于注解类型";

    String REFLECT_ERROR = "通过反射解析对象失败";

    String EMPTY_COLLECTION = "集合为空";

    String WRONG_REQUEST_METHOD = "请求方式异常，不支持此方式：";

    String DATA_NOT_EXIST = "数据不存在";
}
