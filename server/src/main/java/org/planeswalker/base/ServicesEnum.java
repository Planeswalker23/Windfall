package org.planeswalker.base;

import lombok.Getter;

/**
 * 服务枚举类，用以枚举service层的所有服务
 * @author Planeswalker23
 * @date Created in 2019/12/4
 */
@Getter
public enum ServicesEnum {
    /**
     * 用户模块
     */
    UserService("用户模块"),

    /**
     * 评测留言模块
     */
    CommentService("评测留言模块");


    private String serviceName;

    ServicesEnum(String serviceName) {
        this.serviceName = serviceName;
    }
}
