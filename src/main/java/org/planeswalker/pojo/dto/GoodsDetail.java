package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.Goods;

import java.io.Serializable;
import java.util.List;

/**
 * @author Planeswalker23
 * @date Created in 2020/2/10
 */
@Data
public class GoodsDetail extends Goods implements Serializable {

    /**
     * 评论列表
     */
    private List<Comment> comments;
}
