package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体类
 * @author Planeswalker23
 * @date Created in 2020/2/4
 */
@Data
@NoArgsConstructor
public class Goods implements Serializable {

    /**
     * goods 商品表 主键
     */
    @TableId
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产品类型
     */
    private String type;

    /**
     * 型号，系列号
     */
    private String set;

    /**
     * 需求，该产品是用于何处，或解决什么问题
     */
    private String requirement;

    /**
     * 产品介绍
     */
    private String introduce;

    /**
     * 详情
     */
    private String description;

    /**
     * 产品使用方法
     */
    private String usage;

    /**
     * 图片 url
     */
    private String img;

    /**
     * 淘宝跳转 url
     */
    private String taoBaoUrl;

    /**
     * 价格
     */
    private Double price;


    /**
     * 创建记录的时间
     */
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    private Date updateTime;

    /**
     * 乐观锁的版本号
     * 查询出来之后不返回
     */
    @Version
    @JsonIgnore
    private Integer version;
}
