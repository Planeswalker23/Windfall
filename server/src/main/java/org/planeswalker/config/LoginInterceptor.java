package org.planeswalker.config;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.LoginErrors;
import org.planeswalker.base.Response;
import org.planeswalker.exception.LoginException;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.utils.SessionUtil;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
        User userBean;
        // fix: 过滤器的异常不进入统一异常处理机制，需要使用输出流返回
        try {
            userBean = SessionUtil.getUserBean();
        } catch (NotLoginException e) {
            log.warn(e.getMessage(), e);
            this.returnJson(response, e);
            return false;
        }
        String[] userIdArray = parameters.get(Constant.USER_ID);
        // 获取参数中的userId参数数组
        String userId = null;
        if (userIdArray != null) {
            userId = userIdArray[Constant.ZERO];
        }
        // 若参数中包含userId参数，与已登录信息作比较，不同直接抛出异常
        // 若不存在userId参数，只进行是否登录校验（已在SessionUtil.getUserBean()方法中验证）
        if (!StringUtils.isEmpty(userId) && !userBean.getUserId().equals(userId)) {
            log.warn("传入用户信息参数[{}]与登录用户信息[{}]不一致", userId, userBean.getUserId());
            // fix: 过滤器中报错未被统一处理，需要使用输出流返回
            this.returnJson(response, new LoginException(LoginErrors.WRONG_USER));
            return false;
        }
        log.info("登录用户email=[{}]，userName=[{}]", userBean.getEmail(), userBean.getUserName());
        return super.preHandle(request, response, handler);
    }

    /**
     * 过滤器若出现未登录异常，返回输出流
     * fix: 过滤器的异常不进入统一异常处理机制
     * @param response
     * @param exception
     */
    private void returnJson(HttpServletResponse response, Exception exception){
        OutputStream writer = null;
        response.setCharacterEncoding(OutputFormat.Defaults.Encoding);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            writer = response.getOutputStream();
            writer.write(Response.failed(exception.getMessage()).toString().getBytes());
            // 清空缓存并输出流
            writer.flush();
        } catch (IOException e){
            log.error("过滤器输出流异常: {}", e.getMessage(), e);
        } finally {
            // 关闭输出流
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("输出流关闭异常: {}", e.getMessage(), e);
                }
            }
        }
    }
}
