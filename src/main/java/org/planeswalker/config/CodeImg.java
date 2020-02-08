package org.planeswalker.config;

import lombok.Data;
import org.planeswalker.pojo.dto.RegisterDto;
import org.planeswalker.pojo.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码
 * 调用获取验证码接口 {@link org.planeswalker.controller.AuthCodeController#getCodeImg(HttpServletResponse, HttpSession)} 时存放
 * 调用注册接口 {@link org.planeswalker.controller.LoginController#addUser(RegisterDto, UserInfo)}，注册成功后，清除验证码 code
 * @author Planeswalker23
 * @date Created in 2020/2/3
 */
@Data
@Component
public class CodeImg {

    private String code;
}
