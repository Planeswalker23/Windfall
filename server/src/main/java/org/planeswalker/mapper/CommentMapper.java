package org.planeswalker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.planeswalker.pojo.dto.TypeNum;
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
     * 获取各种类文章数目
     * @return
     */
    @Select("select type, count(*) as num from comment where state=1 group by type;")
    List<TypeNum> getTypeNum();
}
