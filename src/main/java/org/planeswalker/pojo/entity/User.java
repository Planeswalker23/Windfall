package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.planeswalker.pojo.dto.LoginDto;

import java.io.Serializable;
import java.util.Date;

/**
 * user表 用户实体类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    /**
     * user表 主键
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 创建记录的时间
     */
    private Date createTime;

    /**
     * 操作时间，写入数据库时自动更新
     */
    private Date updateTime;

    public User(LoginDto loginDto) {
        this.password = loginDto.getPassword();
        this.account = loginDto.getAccount();
    }
}
