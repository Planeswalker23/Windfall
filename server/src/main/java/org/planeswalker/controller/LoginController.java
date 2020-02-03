package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.dto.RegisterDto;
import org.planeswalker.pojo.dto.UserPlusInfo;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.pojo.entity.UserInfo;
import org.planeswalker.service.LoginService;
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

    @Autowired
    private LoginService loginService;

    /**
     * 注册
     * @param register {@link RegisterDto}
     * @param userInfo {@link UserInfo}
     * @return {@link Response}
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public Response<String> addUser(@Valid RegisterDto register, UserInfo userInfo) {
        return Response.success(loginService.register(new User(register), userInfo, register.getImgCode()));
    }

    /**
     * 修改个人信息，此接口不允许修改密码
     * @param newUser {@link User}
     * @param userInfo {@link UserInfo}
     * @return {@link Response}
     */
    @PutMapping("/info")
    @Transactional(rollbackFor = Exception.class)
    public Response updateUserInfo(User newUser, UserInfo userInfo) {
        // 参数验证
        Assert.notNull(newUser, Errors.EMPTY_PARAMS);
        if (StringUtils.isEmpty(newUser.getUserId())) {
            log.warn("userId参数为空，修改个人信息失败");
            return Response.failed(Errors.VALID_ERROR + Constant.MAO_HAO + "userId");
        }
        // 此接口不允许修改密码
        newUser.setPassword(null);
        userInfo.setUserId(newUser.getUserId());
        loginService.updateUserInfo(newUser, userInfo);
        return Response.success();
    }

    /**
     * 登录
     * @param loginDto {@link LoginDto}
     *                 {"email": "1101001@qq.com","password": "123"}
     * @return {@link Response}
     */
    @PostMapping("/login")
    public Response<User> login(@Valid LoginDto loginDto) {
        return Response.success(loginService.login(loginDto));
    }

    /**
     * 获取用户个人信息
     * @param userId
     * @return {@link Response}
     */
    @GetMapping("/info")
    public Response<UserPlusInfo> getUserInfo(String userId) {
        // 参数验证
        Assert.notNull(userId, Errors.EMPTY_PARAMS);
        return Response.success(loginService.getUserInfo(userId));

    }

    /**
     * 注销账户: 根据 userId 将该记录删除
     * @param userId
     * @return {@link Response}
     */
    @DeleteMapping("/myself")
    public Response deleteMyself(String userId) {
        // 参数验证
        Assert.notNull(userId, Errors.EMPTY_PARAMS);
        return Response.success(loginService.deleteMyself(userId));
    }
}
