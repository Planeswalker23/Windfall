package org.planeswalker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.planeswalker.pojo.entity.Goods;
import org.springframework.stereotype.Repository;

/**
 * goods表 持久层
 * @author Planeswalker23
 * @date Created in 2020-02-08
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

}
