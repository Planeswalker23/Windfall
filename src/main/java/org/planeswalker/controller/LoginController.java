package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.dto.RegisterDto;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.LoginService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    /**
     * final没有意义，因为spring本身就是单例的，但这里加上final更容易让人理解
     * 或许是为了防止在程序运行的时候，又执行一遍构造函数
     */
    private final LoginService loginService;

    /**
     * Spring建议："总是在您的bean中使用构造函数建立依赖注入"
     * 推荐使用构造器注入，因为这会更加明确成员变量的加载顺序
     * 同时构造器注入也不需要再使用@Autowird注解，因为本类已委托给Spring容器管理
     * @param loginService 登录service
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 注册
     * @param register {@link RegisterDto}
     *                 {"userName":"dd","password":"1","email":"123@qq.com"}
     * @return {@link Response#toString()}
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public String addUser(@Valid RegisterDto register) {
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
     * @return {@link Response#toString()}
     */
    @PutMapping("/info")
    @Transactional(rollbackFor = Exception.class)
    public String updateUserInfo(User newUser) {
        // 参数验证
        Assert.notNull(newUser, Errors.EMPTY_PARAMS);
        if (StringUtils.isEmpty(newUser.getUserId())) {
            log.warn("userId参数为空，修改个人信息失败");
            return Response.failed(Errors.VALID_ERROR + Constant.MAO_HAO + "userId");
        }
        loginService.updateUserInfo(newUser);
        return Response.success();
    }

    /**
     * 登录
     * @param loginDto {@link LoginDto}
     *                 {"email": "1101001@qq.com","password": "123"}
     * @return {@link Response#toString()}
     */
    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto) {
        return Response.success(loginService.login(loginDto));
    }

    /**
     * 获取用户个人信息
     * @return {@link Response#toString()}
     */
    @GetMapping("/info")
    public String getUserInfo() {
        // 直接获取登录信息的userId，用以获取个人信息
        User user = SessionUtil.getUserBean();
        return Response.success(loginService.getUserInfo(user.getUserId()));

    }
}
