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

    String OPERATING_AUTHORIZATION_ERROR = "没有操作权限";

    String DATA_NOT_EXIST = "数据不存在";

    /**
     * 注册相关
     */
    String WRONG_MAIL = "邮箱格式错误";
    String MAIL_EXIST = "邮箱已被注册";
    String MAIL_NOT_REGISTER = "该邮箱尚未注册";
    String USER_NOT_EXIST = "该用户不存在";
    String CODE_IMG_NOT_EXIST = "验证码不存在，请刷新后重新获取";
    String WRONG_IMG_CODE = "验证码错误";

    /**
     * 登录相关
     */
    String USER_NOT_LOGIN = "用户未登录";
    String WRONG_PASSWORD = "密码错误";
    String WRONG_USER = "用户信息不一致，请刷新或重新登录后重试";
}
