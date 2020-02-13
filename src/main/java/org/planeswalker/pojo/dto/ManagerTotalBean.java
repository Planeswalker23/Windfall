package org.planeswalker.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 后台管理首页，获取统计数据
 * @author Planeswalker23
 * @date Created in 2020/2/13
 */
@Data
public class ManagerTotalBean {

    /**
     * 用户总数
     */
    private Integer users;
    /**
     * 商品总数
     */
    private Integer goods;
    /**
     * 评测总数
     */
    private Integer comments0;
    /**
     * 留言总数
     */
    private Integer commentsOthers;

    /**
     * 最受欢迎的商品
     */
    private List<IndexGoods> list;
}
