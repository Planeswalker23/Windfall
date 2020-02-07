package org.planeswalker.utils;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.pojo.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session工具类
 * @author Planeswalker23
 * @date Created in 2019-11-02
 */
@Slf4j
public class SessionUtil {

    /**
     * 获取session
     * @return {@link javax.servlet.http.HttpSession}
     */
    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 获取已登录的userBean信息
     * @return {@link javax.servlet.http.HttpSession}
     * @throws NotLoginException
     */
    public static User getUserBean() throws NotLoginException {
        User user = (User) getSession().getAttribute(Constant.USER_BEAN);
        if (user == null) {
            // 未登录，抛出异常
            throw new NotLoginException();
        } else {
            // 已登录，返回登录信息
            log.info("获取登录信息成功，用户信息: {}", JacksonUtil.toJson(user));
            return user;
        }
    }

    /**
     * 获取已登录的 userId
     * @throws NotLoginException
     */
    public static String getUserId() throws NotLoginException {
        return getUserBean().getUserId();
    }

    /**
     * 更新 session 中的 user 变量
     * @param user
     */
    public static void updateUserInSession(User user) {
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("user", user);
    }

    /**
     * 更新 session 中的变量
     * @param target
     * @param object
     */
    public static void addIntoSession(String target, Object object) {
        HttpSession session = SessionUtil.getSession();
        session.setAttribute(target, object);
    }

    /**
     * 校验后台管理系统的权限
     */
    public static void checkRootAuthority() {
        // 后台管理页面的权限
        User user = SessionUtil.getUserBean();
        // user 未登录的情况已在工具类中校验
        if (!Constant.ZERO.equals(user.getAuthority())) {
            log.error("没有访问权限");
            throw new NotLoginException();
        }
    }
}
