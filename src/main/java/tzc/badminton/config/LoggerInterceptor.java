package tzc.badminton.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 日志过滤器，过滤所有请求
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
public class LoggerInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路由名
        logger.info("==================Start: [{}]==================", request.getServletPath());
        // 获取请求方式GET/POST
        String method = request.getMethod();
        logger.info("请求方式: {}", method);
        // 执行获取请求参数的策略方法
        Map<String, String[]> parameters = request.getParameterMap();
        logger.info("Args参数: ");
        parameters.forEach((k, v)-> logger.info("\t==> input [{}]:\t{}", k, v));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("==================End: [{}]==================\n", request.getServletPath());
    }
}
