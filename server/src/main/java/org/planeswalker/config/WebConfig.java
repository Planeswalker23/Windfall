package org.planeswalker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    /**
     * Configure cross origin requests processing.
     * 设置跨域访问
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
                // 设置是否允许客户端发送cookie信息
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加日志拦截器，同时添加拦截路由 -> 所有路由
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error");

        // 请求方式拦截器
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");

        // 添加登录校验拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/error", "/h2", "/codeImg", "/favicon.ico");
    }
}
