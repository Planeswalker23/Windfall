package org.planeswalker.base;

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

    /**
     * session中存放user信息的key
     */
    String USER_BEAN = "user";
    String USER_ID = "userId";

    /**
     * 返回message
     */
    String SUCCESS = "成功";
    String FAILED = "失败";
}
