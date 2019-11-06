package tzc.badminton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tzc.badminton.base.Constant;
import tzc.badminton.base.Response;
import tzc.badminton.base.utils.SessionUtil;
import tzc.badminton.module.dto.LoginDto;
import tzc.badminton.module.dto.RegisterDto;
import tzc.badminton.module.entity.User;
import tzc.badminton.service.LoginService;

import javax.validation.Valid;

/**
 * 登录服务控制层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 注册
     * @param register {@link tzc.badminton.module.dto.RegisterDto}
     *                 {"userName":"dd","password":"1","email":"123@qq.com"}
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public String register(@Valid RegisterDto register) {
        logger.info("开始注册业务");
        User newUser = new User();
        BeanUtils.copyProperties(register, newUser);
        return Response.success(loginService.applyUser(newUser));
    }

    /**
     * 修改个人信息
     * @param newUser {@link tzc.badminton.module.entity.User}
     * {
     *     "userId": "c68c512b-55c5-456a-9832-783070166c40",
     *     "userName": "Planeswalker1101002",
     *     "password": "1101001",
     *     "email": "1101001@qq.com"
     * }
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @PostMapping("/modify")
    @Transactional(rollbackFor = Exception.class)
    public String modifyUser(User newUser) {
        // 参数验证
        if (newUser==null || StringUtils.isEmpty(newUser.getUserId())) {
            return Response.failed(Constant.EMPTY_PARAMS);
        }
        logger.info("开始修改个人信息业务");
        loginService.applyUser(newUser);
        return Response.success();
    }

    /**
     * 登录
     * @param loginDto {@link tzc.badminton.module.dto.LoginDto}
     *                 {"email": "1101001@qq.com","password": "123"}
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto) {
        return Response.success(loginService.login(loginDto));
    }

    /**
     * 获取用户个人信息
     * @return {@link tzc.badminton.base.Response} JSON.toJSONString(Response)
     */
    @GetMapping("/userInfo")
    public String userInfo() {
        // 直接获取登录信息的userId，用以获取个人信息
        User user = SessionUtil.getUserBean();
        return Response.success(loginService.getUserInfo(user.getUserId()));

    }
}
