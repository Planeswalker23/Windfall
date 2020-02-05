package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.dto.RegisterDto;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.LoginService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录服务控制层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 注册
     * @param register {@link RegisterDto}
     *                 {"userName":"dd","password":"1","email":"123@qq.com"}
     * @return {@link Response}
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public Response addUser(@Valid RegisterDto register) {
        return Response.success(loginService.register(new User(register)));
    }

    /**
     * 修改个人信息
     * @param newUser {@link User}
     * {
     *     "userId": "c68c512b-55c5-456a-9832-783070166c40",
     *     "userName": "Planeswalker1101002",
     *     "password": "1101001",
     *     "email": "1101001@qq.com"
     * }
     * @return {@link Response}
     */
    @PostMapping("/info")
    @Transactional(rollbackFor = Exception.class)
    public Response updateUserInfo(User newUser) {
        newUser.setUserId(SessionUtil.getUserId());
        loginService.updateUserInfo(newUser);
        return Response.success();
    }

    /**
     * 登录
     * @param loginDto {@link LoginDto}
     *                 {"email": "1101001@qq.com","password": "123"}
     * @return {@link Response}
     */
    @PostMapping("/login")
    public Response login(@Valid LoginDto loginDto,
                          HttpServletResponse response) {
        User user=loginService.login(loginDto);
        String userId = user.getUserId();
        Cookie cookie = new Cookie("userId",userId);
        cookie.setMaxAge(24*3600);
        response.addCookie(cookie);
        return Response.success(user);
    }

    /**
     * 获取用户个人信息
     * @return {@link Response}
     */
    @GetMapping("/info")
    public Response getUserInfo() {
        // 直接获取登录信息的userId，用以获取个人信息
        User user = SessionUtil.getUserBean();
        return Response.success(loginService.getUserInfo(user.getUserId()));

    }



}
