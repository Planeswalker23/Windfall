package org.planeswalker.module.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 登录dto
 * @author Planeswalker23
 * @date Created in 2019-11-02
 */
@Data
public class LoginDto implements Serializable {

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    /**
     * 密码，使用md5加密，代码中加密
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
