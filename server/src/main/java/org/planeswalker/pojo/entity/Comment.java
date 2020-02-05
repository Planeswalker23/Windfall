package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * comment 实体类，作为帖子的实体类
 * @author Planeswalker23
 * @date Created in 2020/2/4
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
     * user表 主键
     */
    private String userId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不可为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不可为空")
    private String content;

    /**
     * 价格
     */
    private Double price;

    /**
     * 购买链接
     */
    private String buyUrl;


    /**
     *  文章类型
     */
    private Integer type;

    /**
     * 图片链接
     */
    private String imgUrl;


    /**
     * 点赞的 userId，返回前端时，需要更新为 num
     */
    private String likeNum;

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

    public Comment(@NotBlank(message = "用户 id 不可为空") String userId) {
        this.userId = userId;
    }
}

