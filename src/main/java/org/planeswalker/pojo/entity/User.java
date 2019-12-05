package org.planeswalker.pojo.entity;

import lombok.Data;

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
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码，使用md5加密，代码中加密
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
}
