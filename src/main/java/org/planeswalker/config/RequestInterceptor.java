package org.planeswalker.config;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理跨域访问的请求拦截器
 * @author fanyidong
 * @date Created in 2018-12-26
 */
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    /**
     * options请求方式
     */
    private static final String OPTIONS = "OPTIONS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("X-Powered-By","Jetty");
        String method= request.getMethod();
        if (OPTIONS.equals(method)){
            response.setStatus(Constant.SUCCESS_CODE);
            return false;
        }
        return true;
    }
}
