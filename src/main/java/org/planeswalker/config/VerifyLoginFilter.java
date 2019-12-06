package org.planeswalker.config;

import org.planeswalker.base.Constant;
import org.planeswalker.base.LoginErrors;
import org.planeswalker.exception.LoginException;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 验证登录的过滤器
 * @author Planeswalker23
 * @date Created in 2019/12/6
 */
@WebFilter(urlPatterns = "/*", filterName = "VerifyLoginFilter")
public class VerifyLoginFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(VerifyLoginFilter.class);

    /**
     * 不需要过滤的路径
     */
    private final String[] notFilterUrls = new String[]{"/user/login","/user/register","/error"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 获得请求地址，判断是否需要跳过过滤
        if (this.passChain(httpServletRequest.getRequestURI())) {
            // 过滤器放行
            chain.doFilter(request, response);
        }
        logger.info("==================验证登录过滤器================");
        Map<String, String[]> parameters = request.getParameterMap();
        // 登录验证，此方法中已包含未登录的验证
        User userBean = SessionUtil.getUserBean();
        String[] userIdArray = parameters.get(Constant.USER_ID);
        // 获取参数中的userId参数数组
        String userId = null;
        if (userIdArray != null) {
            userId = userIdArray[Constant.ZERO];
        }
        // 若参数中包含userId参数，与已登录信息作比较，不同直接抛出异常
        // 若不存在userId参数，只进行是否登录校验（已在SessionUtil.getUserBean()方法中验证）
        if (!StringUtils.isEmpty(userId) && !userBean.getUserId().equals(userId)) {
            logger.warn("传入用户信息参数[{}]与登录用户信息[{}]不一致", userId, userBean.getUserId());
            throw new LoginException(LoginErrors.WRONG_USER);
        }
        chain.doFilter(request, response);
        logger.info("登录用户email=[{}]，userName=[{}]", userBean.getEmail(), userBean.getUserName());
    }

    /**
     * 验证是否需要跳过过滤器
     * @param uri
     * @return boolean
     */
    private boolean passChain(String uri) {
        List<String> notFilterUrlsList = Arrays.asList(notFilterUrls);
        if (notFilterUrlsList.contains(uri)) {
            return true;
        }
        return false;
    }
}
