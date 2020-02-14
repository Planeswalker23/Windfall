package org.planeswalker.base;

/**
 * 登录服务异常提示
 * @author Planeswalker23
 * @date Created in 2019/12/4
 */
public interface LoginErrors {

    /**
     * 注册相关
     */
    String WRONG_MAIL = "邮箱格式错误";
    String MAIL_EXIST = "邮箱已被注册";
    String MAIL_NOT_REGISTER = "该邮箱尚未注册";
    String USER_NOT_EXIST = "该用户不存在";

    /**
     * 登录相关
     */
    String USER_NOT_LOGIN = "用户未登录";
    String WRONG_PASSWORD = "密码错误";
    String WRONG_USER = "用户信息不一致，请刷新或重新登录后重试";
}
