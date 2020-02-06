package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "用户 id 参数不可为空")
    private String userId;

    /**
     * 作者，发表评测的用户的昵称
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 作者，发表评测的用户的昵称
     */
    private String imgUrl;

    /**
     * 评论内容
     */
    @NotBlank(message = "内容参数不可为空")
    private String content;

    /**
     * 点赞人数，实际存的是以逗号分隔的点赞人 userId
     */
    private String likeNum;

    /**
     * 若已登录，此字段表示"我"是否对此 comment 点赞
     */
    @TableField(exist = false)
    private boolean isZan;

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

    public Comment(@NotBlank(message = "用户 id 参数不可为空") String userId) {
        this.userId = userId;
    }
}
