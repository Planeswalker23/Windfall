package tzc.badminton.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tzc.badminton.base.Constant;
import tzc.badminton.base.exception.WindfallException;
import tzc.badminton.base.utils.CheckUtil;
import tzc.badminton.base.utils.NumberUtil;
import tzc.badminton.dto.LoginDto;
import tzc.badminton.entity.User;
import tzc.badminton.mapper.UserMapper;

import java.util.Date;
import java.util.List;

/**
 * 用户服务层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Service
public class LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 注册或修改用户信息
     * @param newUser
     * @return 注册成功返回userId
     *         修改成功返回null
     * @throws WindfallException
     */
    public String applyUser(User newUser) throws WindfallException {
        String email = newUser.getEmail();
        // 邮箱是否为空
        boolean isEmailEmpty = StringUtils.isEmpty(email);
        // 验证邮箱格式，邮箱用于找回密码
        if (!isEmailEmpty && !CheckUtil.checkEmail(email)) {
            throw new WindfallException(Constant.WRONG_MAIL);
        }
        // 根据「邮箱」查询所有匹配的用户
        User selectByEmail = new User();
        selectByEmail.setEmail(newUser.getEmail());
        List<User> resultByEmail = userMapper.selectByExample(selectByEmail);
        if (!CollectionUtils.isEmpty(resultByEmail) && resultByEmail.size() > 0) {
            // 若存在邮箱不相等，且userId不相等的情况，说明想要修改或注册的邮箱已经被使用
            // 注册时根据已存在邮箱查询到的结果userId不等于传参userId:null
            resultByEmail.forEach(resultEntity ->{
                if (email.equals(resultEntity.getEmail()) && !resultEntity.getUserId().equals(newUser.getUserId())) {
                    // 若传入userId为空是注册报错，否则是修改个人信息的报错
                    if (StringUtils.isEmpty(newUser.getUserId())) {
                        throw new WindfallException(Constant.MAIL_EXIST);
                    } else {
                        throw new WindfallException(Constant.USER_NOT_EXIST);
                    }
                }
            });
        }
        if (StringUtils.isEmpty(newUser.getUserId())) {
            // 密码使用md5加密
            newUser.setPassword(NumberUtil.md5(newUser.getPassword()));
            // 添加其他字段
            newUser.setUserId(NumberUtil.createUUId());
            newUser.setCreateTime(new Date());
            if(userMapper.insertSelective(newUser) == 0) {
                logger.warn("日志信息 => 注册失败");
                throw new WindfallException(Constant.FAILED);
            }
            logger.info("日志信息 => 注册成功");
            return newUser.getUserId();
        } else {
            // 根据「邮箱」查询所有匹配的用户
            User selectByUserId = new User();
            selectByUserId.setUserId(newUser.getUserId());
            User resultByUserId = userMapper.selectOne(selectByUserId);
            // 若根据userId无法查询到数据，数据错误
            if (resultByUserId == null) {
                throw new WindfallException(Constant.USER_NOT_EXIST);
            }
            // 若修改了密码，将密码加密
            if (!StringUtils.isEmpty(newUser.getPassword())) {
                newUser.setPassword(NumberUtil.md5(newUser.getPassword()));
            }
            // 修改信息
            if (userMapper.updateByPrimaryKeySelective(newUser) == 0) {
                logger.warn("日志信息 => 修改个人信息失败");
                throw new WindfallException(Constant.EDIT_FAILED);
            }
            logger.info("日志信息 => 修改个人信息成功");
            return null;
        }
    }

    /**
     * 登录服务
     * @param loginDto
     * @return {@link tzc.badminton.entity.User}
     */
    public User login(LoginDto loginDto) {
        // 根据「邮箱」查询
        User selectByEmail = new User();
        selectByEmail.setEmail(loginDto.getEmail());
        User selectByEmailUser = userMapper.selectOne(selectByEmail);
        // 该邮箱尚未注册
        if (selectByEmailUser == null) {
            throw new WindfallException(Constant.MAIL_NOT_REGIST);
        }
        // 验证密码
        if (!selectByEmailUser.getPassword().equals(NumberUtil.md5(loginDto.getPassword()))) {
            throw new WindfallException(Constant.WRONG_PASSWORD);
        }
        logger.info("日志信息 => 登录成功 ***** " + JSON.toJSONString(selectByEmailUser));
        // todo 将已登录用户信息放入session
        return selectByEmailUser;
    }
}
