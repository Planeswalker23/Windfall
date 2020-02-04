package org.planeswalker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.planeswalker.pojo.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * comment表 持久层
 * @author Planeswalker23
 * @date Created in 2020-02-04
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
