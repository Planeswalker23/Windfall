package tzc.badminton.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tzc.badminton.base.Constant;
import tzc.badminton.base.Response;
import tzc.badminton.utils.SessionUtil;
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
@RequestMapping("/user")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

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
     * @param register {@link tzc.badminton.module.dto.RegisterDto}
     *                 {"userName":"dd","password":"1","email":"123@qq.com"}
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public String addUser(@Valid RegisterDto register) {
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
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @PutMapping("/info")
    @Transactional(rollbackFor = Exception.class)
    public String updateUserInfo(User newUser) {
        // 参数验证
        Assert.notNull(newUser, Constant.EMPTY_PARAMS);
        if (StringUtils.isEmpty(newUser.getUserId())) {
            logger.warn("userId参数为空，修改个人信息失败");
            return Response.failed(Constant.VALID_ERROR + Constant.MAO_HAO + "userId");
        }
        logger.info("开始修改个人信息业务");
        loginService.applyUser(newUser);
        return Response.success();
    }

    /**
     * 登录
     * @param loginDto {@link tzc.badminton.module.dto.LoginDto}
     *                 {"email": "1101001@qq.com","password": "123"}
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto) {
        return Response.success(loginService.login(loginDto));
    }

    /**
     * 获取用户个人信息
     * @return {@link tzc.badminton.base.Response} JacksonUtil.toJson(Response)
     */
    @GetMapping("/info")
    public String getUserInfo() {
        // 直接获取登录信息的userId，用以获取个人信息
        User user = SessionUtil.getUserBean();
        return Response.success(loginService.getUserInfo(user.getUserId()));

    }
}
