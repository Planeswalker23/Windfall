package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 * @author Planeswalker23
 * @date Created in 2020/2/3
 */
@Data
public class UserInfo implements Serializable {

    /**
     * user_info表 主键
     */
    @TableId
    private String userId;

    /**
     * 关注的内容，中文逗号分隔 彩妆，香水，护肤品
     */
    private String favourite;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 所有评测的点赞数
     * @TableField exist = false 表示该属性不为数据库表字段，但又是必须使用的
     */
    @TableField(exist = false)
    private int totalLikeNum;

    /**
     * 创建记录的时间
     */
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    private Date updateTime;
}
