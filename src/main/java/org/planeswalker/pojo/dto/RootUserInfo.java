package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.utils.JacksonUtil;

import java.util.Date;

/**
 * 管理后台，查询用户信息
 * @author Planeswalker23
 * @date Created in 2020/2/8
 */
@Data
public class RootUserInfo {

    /**
     * 序号
     */
    private Integer no;

    /**
     * user表 主键
     */
    private String userId;

    /**
     * 用户名，昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱，不可修改
     */
    private String email;

    /**
     * 创建记录的时间
     */
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    private Date updateTime;

    /**
     * 启用状态 0-禁用 1-启用，默认为0-禁用
     */
    private Integer state;

    /**
     * 权限 0-管理员 1-普通用户，默认为1-普通用户
     */
    private Integer authority;

    /**
     * 关注的内容，中文逗号分隔 彩妆，香水，护肤品
     */
    private String favourite;

    /**
     * 个性签名
     */
    private String signature;

    public String getCreateTime() {
        return JacksonUtil.date2String(this.createTime);
    }

    public String getUpdateTime() {
        return JacksonUtil.date2String(this.updateTime);
    }
}
