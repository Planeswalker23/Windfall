package org.planeswalker.base;

/**
 * 页面枚举类
 * @author Planeswalker23
 * @date Created in 2020/3/3
 */
public enum Templates {

    /**
     * 登录页面
     */
    LOGIN("login"),
    /**
     * 错误页面
     */
    ERROR("error");
    /**
     * 模板名称
     */
    private String name;

    Templates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
