package org.planeswalker.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.config.CodeImg;
import org.planeswalker.exception.WindfallException;
import org.planeswalker.mapper.UserInfoMapper;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.dto.UserPlusInfo;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.pojo.entity.UserInfo;
import org.planeswalker.utils.CheckUtil;
import org.planeswalker.utils.CollectionUtil;
import org.planeswalker.utils.JacksonUtil;
import org.planeswalker.utils.NumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户服务层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CodeImg codeImg;
    @Autowired
    private CommentService commentService;

    /**
     * 注册业务
     * @param newUser
     * @param userInfo
     * @param imgCode 验证码
     * @return userId {@link User#getEmail()}
     */
    public String register(User newUser, UserInfo userInfo, String imgCode) {
        log.info("开始注册业务");
        // 验证码校验
        if (codeImg == null || StringUtils.isEmpty(codeImg.getCode())) {
            throw new WindfallException(Errors.CODE_IMG_NOT_EXIST);
        }
        if (!imgCode.equalsIgnoreCase(codeImg.getCode())) {
            throw new WindfallException(Errors.WRONG_IMG_CODE);
        }
        // 清除验证码
        codeImg.setCode(null);
        // 验证邮箱格式，已验证email属性是否为空
        CheckUtil.checkEmail(newUser.getEmail());
        // 根据「邮箱」查询所有匹配的用户，邮箱不允许重复
        List<User> sameEmailUsers = this.getUserByEmail(newUser.getEmail());
        if (!CollectionUtils.isEmpty(sameEmailUsers)) {
            throw new WindfallException(Errors.MAIL_EXIST);
        }
        // 添加其他字段
        newUser.setUserId(NumberUtil.createUuId());
        newUser.setCreateTime(new Date());
        userInfo.setCreateTime(new Date());
        userInfo.setUserId(newUser.getUserId());
        if(userMapper.insert(newUser)==0
                // 新增插入 user_info 表
                ||userInfoMapper.insert(userInfo)==0) {
            log.warn("注册失败");
            throw new WindfallException(Constant.FAILED);
        }
        log.info("注册成功");
        return newUser.getUserId();
    }
    /**
     * 修改用户信息业务
     * @param newUser
     * @param userInfo
     * @return 注册成功返回userId
     *         修改成功返回null
     */
    public void updateUserInfo(User newUser, UserInfo userInfo) {
        log.info("开始修改个人信息业务");
        // 验证邮箱格式，邮箱用于找回密码
        CheckUtil.checkEmail(newUser.getEmail());
        // 根据相同「邮箱」，不同「userId」的所有匹配的用户
        List<User> sameEmailUsers = userMapper.selectList(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, newUser.getEmail())
                .ne(User::getUserId, newUser.getUserId()));
        // 若除本人外有其他相同的email存在（及邮箱重复），不允许修改
        if (!CollectionUtils.isEmpty(sameEmailUsers)) {
            throw new WindfallException(Errors.MAIL_EXIST);
        }
        // 根据「userId」查询匹配的用户
        User resultByUserId = this.getUserByUserId(newUser.getUserId());
        // 更新前添加旧版本号
        newUser.setVersion(resultByUserId.getVersion());
        // 防止 user_info 信息不修改的情况下 update 字段为空的错误
        userInfo.setUpdateTime(new Date());
        // 修改信息
        if (userMapper.updateById(newUser) == 0) {
            log.warn("修改个人信息失败");
            throw new WindfallException(Errors.EDIT_FAILED);
        }
        // 新增更新 user_info 表
        userInfoMapper.updateById(userInfo);
        log.info("修改个人信息成功");
    }

    /**
     * 登录业务
     * @param loginDto {@link LoginDto}
     * @return {@link User}
     */
    public User login(LoginDto loginDto) {
        log.info("开始登录业务");
        // 根据「邮箱」查询
        List<User> sameEmailUsers = this.getUserByEmail(loginDto.getEmail());
        if (CollectionUtils.isEmpty(sameEmailUsers)) {
            throw new WindfallException(Errors.MAIL_NOT_REGISTER);
        }
        // 验证是否根据邮箱只查询出一条记录
        CollectionUtil.isOneDate(sameEmailUsers);
        User sameEmailUser = sameEmailUsers.get(Constant.ZERO);
        // 验证密码
        if (!sameEmailUser.getPassword().equals(loginDto.getPassword())) {
            throw new WindfallException(Errors.WRONG_PASSWORD);
        }
        log.info("登录成功 ==> {}", JacksonUtil.toJson(sameEmailUser));
        // 登录成功后返回用户信息
        return sameEmailUser;
    }

    /**
     * 获取用户个人信息业务
     * @param userId
     * @return {@link User}
     */
    public UserPlusInfo getUserInfo(String userId) {
        log.info("开始获取用户个人信息业务");
        // 根据「userId」获取用户信息
        User selectByUserIdEntity = this.getUserByUserId(userId);
        log.info("用户个人信息: {} ", JacksonUtil.toJson(selectByUserIdEntity));
        UserPlusInfo userPlusInfo = new UserPlusInfo();
        BeanUtils.copyProperties(selectByUserIdEntity, userPlusInfo);
        // 根据「userId」获取用户基本信息
        UserInfo userInfoByUserId = userInfoMapper.selectById(userId);
        if (userInfoByUserId != null) {
            log.info("用户基本信息: {} ", JacksonUtil.toJson(userInfoByUserId));
            BeanUtils.copyProperties(userInfoByUserId, userPlusInfo);
        }
        // 根据 userId 计算该用户的所有评测的合计点赞数
        userPlusInfo.setTotalLikeNum(commentService.getMyTotalLikeNums(new Comment(userId)));
        return userPlusInfo;
    }

    /**
     * 根据邮箱查询所有匹配的user
     * @param email not null or empty
     * @return List<User> {@link User}
     * @throws {@link WindfallException}
     */
    private List<User> getUserByEmail(String email) throws WindfallException {
        List<User> sameEmailUsers = userMapper.selectList(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email));
        log.info("根据邮箱查询所有匹配用户的结果：{}条", sameEmailUsers.isEmpty()?Constant.ZERO:sameEmailUsers.size());
        return sameEmailUsers;
    }

    /**
     * 根据userId查询唯一匹配的user
     * @param userId
     * @return {@link User}
     * @throws {@link WindfallException}
     */
    public User getUserByUserId(String userId) throws WindfallException {
        User sameUserIdUser = userMapper.selectById(userId);
        if (sameUserIdUser == null) {
            throw new WindfallException(Errors.USER_NOT_EXIST);
        }
        return sameUserIdUser;
    }

    /**
     * 注销账户: 根据 userId 将该记录删除
     * @param userId
     */
    public Integer deleteMyself(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new WindfallException(Errors.EMPTY_PARAMS);
        }
        // 根据 userId 将该条记录删除
        return userMapper.deleteById(userId);
    }
}
