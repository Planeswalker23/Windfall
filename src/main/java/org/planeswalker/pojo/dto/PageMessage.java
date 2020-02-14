package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.base.Constant;

/**
 * 分页实体
 * @author Planeswalker23
 * @date Created in 2020/2/4
 */
@Data
public class PageMessage {

    /**
     * 页码
     */
    private Integer pageNum = Constant.ONE;

    /**
     * 每页数目
     */
    private Integer pageSize = Constant.TEN;
}
