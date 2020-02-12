package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 评测表
 * @author Planeswalker23
 * @date Created in 2020/2/3
 */
@Data
@NoArgsConstructor
public class Comment implements Serializable {

    /**
     * comment表 主键
     */
    @TableId
    private String commentId;

    /**
     * 此comment的父类id，0-评测，其它uuid-留言
     */
    private String commentPid;

    /**
     * user表 主键
     */
    private String userId;

    /**
     * 商品 id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String goodsName;

    /**
     * 作者，发表评测的用户的昵称
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 评论标题
     */
    private String title;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞人数，实际存的是以逗号分隔的点赞人 userId
     */
    private String likeNum;

    /**
     * 若已登录，此字段表示"我"是否对此 comment 点赞
     */
    @TableField(exist = false)
    private boolean zan;

    /**
     * 创建记录的时间
     */
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    private Date updateTime;

    public Comment(String userId) {
        this.userId = userId;
    }
}
