package org.planeswalker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.planeswalker.pojo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * user表 持久层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
