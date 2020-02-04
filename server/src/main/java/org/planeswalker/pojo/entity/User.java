package org.planeswalker.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.planeswalker.base.Constant;
import org.planeswalker.pojo.dto.RegisterDto;

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
     * 邮箱，不可修改
     */
    private String email;

    /**
     * 权限 0-管理员 1-普通用户，默认为1
     */
    private Integer authority = Constant.ONE;

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

    public User(RegisterDto registerDto) {
        this.userName = registerDto.getUserName();
        this.password = registerDto.getPassword();
        this.email = registerDto.getEmail();
    }
}
