package org.planeswalker.service;

import org.planeswalker.base.Constant;
import org.planeswalker.base.LoginErrors;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.LoginException;
import org.planeswalker.exception.WindfallException;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 用户服务层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final UserMapper userMapper;

    @Autowired(required = false)
    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 注册或修改用户信息
     * @param newUser
     * @return 注册成功返回userId
     *         修改成功返回null
     */
    public String applyUser(User newUser) {
        String email = newUser.getEmail();
        // 邮箱是否为空
        boolean isEmailEmpty = StringUtils.isEmpty(email);
        // 验证邮箱格式，邮箱用于找回密码
        if (!isEmailEmpty && !CheckUtil.checkEmail(email)) {
            throw new WindfallException(LoginErrors.WRONG_MAIL);
        }
        // 根据「邮箱」查询所有匹配的用户
        User selectByEmail = new User();
        selectByEmail.setEmail(newUser.getEmail());
        List<User> resultByEmail = userMapper.select(selectByEmail);
        if (!CollectionUtils.isEmpty(resultByEmail) && resultByEmail.size() > 0) {
            // 若存在邮箱不相等，且userId不相等的情况，说明想要修改或注册的邮箱已经被使用
            // 注册时根据已存在邮箱查询到的结果userId不等于传参userId:null
            resultByEmail.forEach(resultEntity ->{
                if (email.equals(resultEntity.getEmail()) && !resultEntity.getUserId().equals(newUser.getUserId())) {
                    // 若传入userId为空是注册报错，否则是修改个人信息的报错
                    if (StringUtils.isEmpty(newUser.getUserId())) {
                        throw new LoginException(LoginErrors.MAIL_EXIST);
                    } else {
                        throw new LoginException(LoginErrors.USER_NOT_EXIST);
                    }
                }
            });
        }
        if (StringUtils.isEmpty(newUser.getUserId())) {
            newUser.setPassword(newUser.getPassword());
            // 添加其他字段
            newUser.setUserId(NumberUtil.createUuId());
            newUser.setCreateTime(new Date());
            if(userMapper.insertSelective(newUser) == 0) {
                logger.warn("注册失败");
                throw new LoginException(Constant.FAILED);
            }
            logger.info("注册成功");
            return newUser.getUserId();
        } else {
            // 根据「邮箱」查询所有匹配的用户
            User selectByUserId = new User();
            selectByUserId.setUserId(newUser.getUserId());
            User resultByUserId = userMapper.selectOne(selectByUserId);
            // 若根据userId无法查询到数据，数据错误
            if (resultByUserId == null) {
                throw new LoginException(LoginErrors.USER_NOT_EXIST);
            }
            // 更新前添加旧版本号
            newUser.setVersion(resultByUserId.getVersion());
            // 修改信息
            if (userMapper.updateByPrimaryKeySelective(newUser) == 0) {
                logger.warn("修改个人信息失败");
                throw new LoginException(Errors.EDIT_FAILED);
            }
            logger.info("修改个人信息成功");
            return null;
        }
    }

    /**
     * 登录服务
     * @param loginDto
     * @return {@link User}
     */
    public User login(LoginDto loginDto) {
        // 根据「邮箱」查询
        User selectByEmail = new User();
        selectByEmail.setEmail(loginDto.getEmail());
        // selectOne方法若查询结果为空会报错
        List<User> usersBySameEmail = userMapper.select(selectByEmail);
        // 该邮箱尚未注册
        if (CollectionUtils.isEmpty(usersBySameEmail)) {
            throw new LoginException(LoginErrors.MAIL_NOT_REGISTER);
        }
        // 验证是否根据邮箱只查询出一条记录
        CollectionUtil.isOneDate(usersBySameEmail);
        User selectByEmailUser = usersBySameEmail.get(Constant.ZERO);
        // 验证密码
        if (!selectByEmailUser.getPassword().equals(loginDto.getPassword())) {
            throw new LoginException(LoginErrors.WRONG_PASSWORD);
        }
        logger.info("登录成功，用户信息: {}", JacksonUtil.toJson(selectByEmailUser));
        // 将已登录用户信息放入session
        HttpSession session = SessionUtil.getSession();
        session.setAttribute(Constant.USER_BEAN, selectByEmailUser);
        logger.info("登录成功, {} 的sessionId ==> {} 登录信息 ==> {}",
                Constant.USER_BEAN, session.getId(), JacksonUtil.toJson(selectByEmailUser));
        // 登录成功后返回用户信息
        return selectByEmailUser;
    }

    /**
     * 根据userId获取用户个人信息
     * @param userId
     * @return
     */
    public User getUserInfo(String userId) {
        // 根据「userId」获取用户信息
        User selectByUserIdCondition = new User();
        selectByUserIdCondition.setUserId(userId);
        User selectByUserIdEntity = userMapper.selectOne(selectByUserIdCondition);
        if (selectByUserIdEntity == null) {
            throw new LoginException(LoginErrors.USER_NOT_EXIST);
        }
        logger.info("用户个人信息: {} ", JacksonUtil.toJson(selectByUserIdEntity));
        return selectByUserIdEntity;
    }
}
