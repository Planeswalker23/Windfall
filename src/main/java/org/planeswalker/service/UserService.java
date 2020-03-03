package org.planeswalker.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.WindfallException;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.LoginDto;
import org.planeswalker.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * user 表 service 层
 * @author Planeswalker23
 * @date Created in 2020/3/3
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录服务方法
     * @param loginDto 参数使用{@link Valid}修饰，表示需要进行参数校验，账号和密码都不能为空
     * @return 登录 success -返回已登录的 {@link User}，登录 failed - 返回null
     */
    public User login(@Valid LoginDto loginDto) {
        // 根据账户查询匹配的用户，若匹配多个会被异常捕获，且account字段有唯一索引
        User userInDb = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, loginDto.getAccount()));
        // 若根据账户查询结果为null，说明该账户未注册
        if (userInDb == null) {
            throw new WindfallException(Errors.USER_NOT_REGISTER);
        }
        // loginDto#getPassword字段已经过参数校验，不会为null
        if (!loginDto.getPassword().equals(userInDb.getPassword())) {
            throw new WindfallException(Errors.USER_WRONG_PASSWORD);
        }
        return userInDb;
    }
}
