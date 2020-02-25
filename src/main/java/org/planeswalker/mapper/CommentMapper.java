package org.planeswalker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.planeswalker.pojo.dto.IndexGoods;
import org.planeswalker.pojo.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * comment表 持久层
 * @author Planeswalker23
 * @date Created in 2020-02-04
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取首页评测数
     * @return
     */
    @Select("select goods_id, count(*) as commentsNum from comment where comment_pid='0' group by goods_id order by commentsNum, title limit 8")
    List<IndexGoods> getIndexGoods();

}
