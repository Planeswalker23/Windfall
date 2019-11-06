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
     * 符号
     */
    String MAO_HAO = "：";
    String DOU_HAO = "，";

    String GET = "GET";
    String POST = "POST";

    String USER_BEAN = "userBean";
    String USER_ID = "userId";

    /**
     * 返回message
     */
    String SUCCESS = "成功";

    String FAILED = "失败";

    String USER_NOT_LOGIN = "用户未登录";

    String EDIT_FAILED = "未修改任何数据";

    String MAIL_NOT_REGIST = "该邮箱尚未注册";

    String CAN_NOT_FIND_USER = "无法找到匹配用户,请重新申请";

    String USER_NOT_EXIST = "该用户不存在";

    String WRONG_PASSWORD = "密码错误";

    String WRONG_MAIL = "邮箱格式错误";

    String MAIL_EXIST = "邮箱已被注册";

    String EMPTY_PARAMS = "参数为空";

    String WRONG_DATA = "数据错误，请联系管理员";

    String SYSTEM_ERROR = "系统错误，请联系系统管理员";

    String DATE_FORMAT_TO_SECOND = "yyyy-MM-dd HH:mm:ss";

    String WRONG_USER = "用户信息不一致，请刷新或重新登录后重试";

    /**
     * 服务名
     */
    String LOGIN_SERVICE = "[登录服务]";

    String VALID_ERROR = "参数校验失败";


}
