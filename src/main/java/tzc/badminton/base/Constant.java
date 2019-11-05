package tzc.badminton.base;

/**
 * 常量接口类，所有魔法值需写在此类中
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public interface Constant {
    /**
     * 数字0~9
     */
    Integer ZERO = 0;
    Integer ONE = 1;
    Integer TWO = 2;
    Integer THREE = 3;
    Integer FOUR = 4;
    Integer FIVE = 5;
    Integer SIX = 6;
    Integer SEVEN = 7;
    Integer EIGHT = 8;
    Integer NINE = 9;
    Integer TEN = 10;
    Integer TWENTY_FOUR = 24;

    /**
     * 状态码
     */
    Integer REQUEST_SUCCESS_CODE = 200;
    Integer REQUEST_FAILED_CODE = 500;

    /**
     * 符号
     */
    String MAO_HAO = "：";
    String DOU_HAO = "，";

    String GET = "get";

    String POST = "post";

    String USER_BEAN = "userBean";

    String EDIT_FAILED = "未修改任何数据";

    String SEND_MAIL_SUCCESS = "已经发送找回密码安全码到您邮箱。请在30分钟内重置密码";

    String MAIL_NOT_REGIST = "该邮箱尚未注册";

    String FIND_PASSWORD = "找回密码";

    String WORNING = "警告";

    String MAIL_MESS_RESET_PASSWORD = "请勿回复本邮件，请复制下面的安全码,重设密码\n\n注意:安全码超过30分钟将会失效，需要重新申请找回密码\n\n安全码为： ";

    String CAN_NOT_FIND_USER = "无法找到匹配用户,请重新申请";

    String CAN_NOT_FIND_MAIL = "请确认是否已发送安全码";

    String LINK_EXPIRED = "安全码已过期,请重新申请";

    String LINK_NOT_EXPIRED = "安全码未过期,不需要重新发送";

    String WRONG_LINK = "安全码或邮箱不正确,请重新申请";

    String USER_NOT_EXIST = "该用户不存在";

    String WRONG_PASSWORD = "密码错误";

    String WRONG_MAIL = "邮箱格式错误";

    String MAIL_EXIST = "邮箱已被注册";

    String USER_EXIST = "用户已存在";

    String EMPTY_PARAMS = "参数为空";

    String WRONG_DATA = "数据错误，请联系管理员";

    String USER_NOT_LOGIN = "用户未登录";

    String SYSTEM_ERROR = "系统错误，请联系系统管理员";

    String VERIFY_OBJECT_CAN_NOT_BE_EMPTY = "校验对象不能为空";

    String DATE_FORMAT_TO_SECOND = "yyyy-MM-dd HH:mm:ss";

    String RESPONSE_EMPTY = "返回为空";

    String HTTP = "http";

    String HTTPS = "https";

    String LOGIN_SERVICE = "[登录服务]";

    String SUCCESS = "成功";

    String FAILED = "失败";

    String XMLHTTPREQUEST = "XMLHttpRequest";

    /**
     * 请求后缀
     */
    String DO = ".do";

    String POST_PARAMS_FORMAT_ERROR = "Post请求参数格式不符合要求";

    String VALID_ERROR = "参数校验失败";


}
