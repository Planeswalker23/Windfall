package org.planeswalker.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息 + 基本信息
 * @author Planeswalker23
 * @date Created in 2020/2/3
 */
@Data
public class UserPlusInfo implements Serializable {

    /**
     * user表 主键
     */
    @TableId
    private String userId;

    /**
     * 用户名，昵称
     */
    private String userName;

    /**
     * 邮箱，不可修改
     */
    private String email;

    /**
     * 关注的内容，中文逗号分隔 彩妆，香水，护肤
     */
    private String favourite;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 所有评测的点赞数
     */
    private int totalLikeNum;
}
