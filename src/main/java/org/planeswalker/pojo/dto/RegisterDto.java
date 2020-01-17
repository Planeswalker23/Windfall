package org.planeswalker.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用于用户注册或修改个人信息的dto
 * @author Planeswalker23
 * @date Created in 2019-11-02
 */
@Data
public class RegisterDto implements Serializable {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 邮箱，不可修改
     */
    @NotEmpty(message = "邮箱不能为空")
    private String email;
}
