package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.base.Response;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.exception.WindfallException;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.RootCommentInfo;
import org.planeswalker.pojo.dto.RootGoodsInfo;
import org.planeswalker.pojo.dto.RootUserInfo;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.Goods;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.LoginService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理系统接口 控制层，返回 template 页面
 * @author Planeswalker23
 * @date Created in 2020/2/8
 */
@Slf4j
@Controller
public class ManagerController {

    @Autowired
    private RootController rootController;

    @Autowired
    private LoginService loginService;

    @GetMapping({"/", "/login"})
    public String managerLogin() {
        // 清空用户 session 缓存
        SessionUtil.updateAttribute(Constant.USER_BEAN, null);
        return "login";
    }

    /**
     * 后台管理系统：首页
     * @param model 模板实体类，可在页面中存放变量
     * @param response
     * @return
     */
    @GetMapping("/index")
    public String managerIndex(Model model, HttpServletResponse response) {
        String res = this.getUserBeanTryCatch(model, response);
        if (res != null) {
            return res;
        }
        // 获取首页数据
        model.addAttribute("total", rootController.getManagerTotalBean());
        return "index";
    }

    /**
     * 后台管理系统：用户管理
     * @param keyword 查询条件
     * @param user 查询条件，按需
     * @param pageMessage
     * @param model 模板实体类，可在页面中存放变量
     * @param response
     * @return
     */
    @GetMapping({"/users", "/searchUsers"})
    public String managerUsers(String keyword, User user, PageMessage pageMessage, Model model, HttpServletResponse response) {
        // 权限校验
        String res = this.getUserBeanTryCatch(model, response);
        if (res != null) {
            return res;
        }
        Response<PageInfo<RootUserInfo>> pageInfoResponse;
        if (StringUtils.isEmpty(keyword)) {
            pageInfoResponse =  rootController.getAllUsers(user, pageMessage);
        } else {
            pageInfoResponse = rootController.searchUsers(keyword, pageMessage);
        }
        model.addAttribute("pageInfo", pageInfoResponse.getRes());
        return "users";
    }

    /**
     * 商品管理页面
     * @param searchType 搜索的商品类型
     * @param keyword 搜索关键词，支持类型、名称、品牌、型号、需求
     * @param goods 按需搜索所有商品
     * @param pageMessage
     * @param model
     * @param response
     * @return
     */
    @GetMapping({"/goods", "/searchGoods"})
    public String goodsManager(String searchType, String keyword, Goods goods, PageMessage pageMessage, Model model, HttpServletResponse response) {
        // 权限校验
        String res = this.getUserBeanTryCatch(model, response);
        if (res != null) {
            return res;
        }
        Response<PageInfo<RootGoodsInfo>> pageInfoResponse;
        if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(searchType)) {
            pageInfoResponse =  rootController.getAllGoods(goods, pageMessage);
        } else {
            pageInfoResponse = rootController.searchGoods(searchType, keyword, pageMessage);
        }
        model.addAttribute("pageInfo", pageInfoResponse.getRes());
        return "goods";
    }

    /**
     * 商品管理页面
     * @param keyword 搜索关键词，支持标题、内容
     * @param comment 按需搜索所有商品
     * @param pageMessage
     * @param model
     * @param response
     * @return
     */
    @GetMapping({"/comments", "/searchComments"})
    public String commentsManager(String keyword, Comment comment, PageMessage pageMessage, Model model, HttpServletResponse response) {
        // 权限校验
        String res = this.getUserBeanTryCatch(model, response);
        if (res != null) {
            return res;
        }
        PageInfo<RootCommentInfo> pageInfoResponse;
        // 评测
        comment.setCommentPid(Constant.ZERO.toString());
        if (StringUtils.isEmpty(keyword)) {
            pageInfoResponse =  rootController.getAllComments(comment, pageMessage);
        } else {
            pageInfoResponse = rootController.searchComments(keyword, comment, pageMessage);
        }
        model.addAttribute("pageInfo", pageInfoResponse);
        return "comments";
    }


    /**
     * 后台管理：校验用户是否登录，若未登录返回登录页
     * @param model
     * @return (null || login)
     */
    private String getUserBeanTryCatch(Model model, HttpServletResponse response) {
        try {
            // 获取 userId
            User user = SessionUtil.getUserBean();
            // 查询数据库中最新的权限
            User rightNowUser = loginService.getUserByUserId(user.getUserId());
            if (!Constant.ZERO.equals(rightNowUser.getAuthority())) {
                throw new WindfallException(Errors.OPERATING_AUTHORIZATION_ERROR);
            }
            model.addAttribute(Constant.USER_BEAN, rightNowUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 错误信息
            String message;
            if (e instanceof WindfallException || e instanceof NotLoginException) {
                message = e.getMessage();
            } else {
                message = Errors.SYSTEM_ERROR;
            }
            model.addAttribute(Constant.REASON, message);
            return "login";
        }
        return null;
    }
}
