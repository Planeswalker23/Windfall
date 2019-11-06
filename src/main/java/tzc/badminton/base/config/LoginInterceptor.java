package tzc.badminton.base.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tzc.badminton.base.Constant;
import tzc.badminton.base.exception.LoginException;
import tzc.badminton.base.utils.SessionUtil;
import tzc.badminton.module.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录验证拦截器，排除登录和注册接口
 * @author Planeswalker23
 * @date Created in 2019-11-06
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameters = request.getParameterMap();
        // 登录验证，此方法中已包含未登录的验证
        User user = SessionUtil.getUserBean();
        String[] userIdArray = parameters.get(Constant.USER_ID);
        // 获取参数中的userId参数数组
        String userId = null;
        if (userIdArray != null) {
            userId = userIdArray[Constant.ZERO];
        }
        // 若参数中包含userId参数，与已登录信息作比较，不同直接抛出异常
        // 若不存在userId参数，只进行是否登录校验
        if (!StringUtils.isEmpty(userId) && !user.getUserId().equals(userId)) {
            logger.warn("传入用户信息参数[{}]与登录用户信息[{}]不一致", JSON.toJSONString(userId), JSON.toJSONString(user.getUserId()));
            throw new LoginException(Constant.WRONG_USER);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
