package org.planeswalker.controller;

import org.planeswalker.base.Templates;
import org.planeswalker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 管理后台页面跳转接口
 * @author Planeswalker23
 * @date Created in 2020/2/27
 */
@Controller
public class ManagingController {

    @Autowired
    private UserService userService;

    @GetMapping({"/login","/login.html"})
    public String loginPage() {
        return Templates.LOGIN.getName();
    }
}
