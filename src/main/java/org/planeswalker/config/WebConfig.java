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
        // 添加日志拦截器，同时添加拦截路由 -> 所有路由
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");
        // 添加登录验证拦截器，在日志拦截器之后，需要添加已注册的mapping
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                // 不经过登录拦截器的路由
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/register");
    }
}
