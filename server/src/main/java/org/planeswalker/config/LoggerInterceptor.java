package org.planeswalker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 日志过滤器，过滤所有请求
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路由名
        log.info("==================Start, 请求路由: [{}]==================", request.getServletPath());
        // 获取请求方式GET/POST
        String method = request.getMethod();
        log.info("请求方式: {}", method);
        // 执行获取请求参数的策略方法
        Map<String, String[]> parameters = request.getParameterMap();
        log.info("Args参数: ");
        parameters.forEach((k, v)-> log.info("\t==> input [{}]:\t{}", k, v));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("==================End, 请求路由: [{}]==================\n", request.getServletPath());
    }
}
