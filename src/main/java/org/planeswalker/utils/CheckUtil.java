package org.planeswalker.utils;

import org.planeswalker.base.Errors;
import org.planeswalker.base.LoginErrors;
import org.planeswalker.exception.WindfallException;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
public class CheckUtil {

    /**
     * 标准email格式的正则表达式验证
     */
    private static final String EmailFormatRule = "^[A-Za-zd0-9]+([-_.][A-Za-zd0-9]+)*@([A-Za-zd0-9]+[-.])+[A-Za-zd]{2,5}$";

    /**
     * 验证邮箱格式，若不符合邮箱格式直接报错
     * @param email
     * @throws {@link WindfallException}
     */
    public static void checkEmail(String email) {
        // 验证入参是否为空
        if (StringUtils.isEmpty(email)) {
            throw new WindfallException(Errors.EMPTY_PARAMS);
        }
        Pattern p = Pattern.compile(EmailFormatRule);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new WindfallException(LoginErrors.WRONG_MAIL);
        }
    }
}
