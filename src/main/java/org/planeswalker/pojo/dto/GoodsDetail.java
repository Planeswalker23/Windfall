package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.Goods;

import java.util.List;

/**
 * @author Planeswalker23
 * @date Created in 2020/2/10
 */
@Data
public class GoodsDetail extends Goods {

    /**
     * 评论列表
     */
    private List<Comment> comments;
}
