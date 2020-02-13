package org.planeswalker.pojo.dto;

import lombok.Data;

/**
 * @author Planeswalker23
 * @date Created in 2020/2/13
 */
@Data
public class IndexGoods {
    /**
     * 序号
     */
    private Integer no;
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 产品类型
     */
    private String type;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 价格
     */
    private Double price;
    /**
     * 评测数
     */
    private Integer commentsNum;
}
