package org.planeswalker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 * 添加拦截器
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录校验拦截器
        /*registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/error", "/h2");*/

        // 添加日志拦截器，同时添加拦截路由 -> 所有路由
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");
    }
}
