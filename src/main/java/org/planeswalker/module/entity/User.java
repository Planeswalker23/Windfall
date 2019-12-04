package org.planeswalker.module.entity;

import lombok.Data;
import org.planeswalker.annotation.Encrypt;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * user表 用户实体类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Data
public class User implements Serializable {

    /**
     * user表 主键
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码，使用md5加密，代码中加密
     */
    @Encrypt
    private String password;

    /**
     * 邮箱，不可修改
     */
    private String email;

    /**
     * 创建记录的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    @Column(name = "update_time")
    private Date updateTime;
}
