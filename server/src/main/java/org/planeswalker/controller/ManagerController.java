package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.LoginService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 后台管理系统接口 控制层，返回 template 页面
 * @author Planeswalker23
 * @date Created in 2020/2/8
 */
@Slf4j
@Controller
public class ManagerController {

    @Autowired
    private LoginService loginService;


    @GetMapping({"/", "/login"})
    public String managerLogin() {
        return "login";
    }

    /**
     * 后台管理系统：首页
     * @param model 模板实体类，可在页面中存放变量
     * @return
     */
    @GetMapping("/index")
    public String managerIndex(Model model) {
//        String res = this.getUserBeanTryCatch(model);
//        if (res != null) {
//            return res;
//        }
        User user = loginService.getUserByUserId("root");
        model.addAttribute(Constant.USER_BEAN, user);
        return "index";
    }

    /**
     * 后台管理：校验用户是否登录，若未登录返回登录页
     * @param model
     * @return (null || login)
     */
    private String getUserBeanTryCatch(Model model) {
        try {
            User user = SessionUtil.getUserBean();
            model.addAttribute(Constant.USER_BEAN, user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 错误信息
            String message;
            if (e instanceof WindfallException) {
                message = e.getMessage();
            } else {
                message = Errors.SYSTEM_ERROR;
            }
            model.addAttribute(Constant.REASON, message);
            return "login";
        }
        return null;
    }
}
