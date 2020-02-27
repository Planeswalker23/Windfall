package org.planeswalker.config;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.utils.SessionUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录拦截器，拦截除指定路由外其余未登录的请求
 * @author Planeswalker23
 * @date Created in 2020/1/21
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==================验证登录过滤器================");
        Map<String, String[]> parameters = request.getParameterMap();
        // 登录验证，此方法中已包含未登录的验证
        // todo 返回登录页
        User user = SessionUtil.getUserBean();
        log.info("登录用户account=[{}]，userName=[{}]", user.getAccount(), user.getUserName());
        return super.preHandle(request, response, handler);
    }
}
