package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * page 访问
 * @author Planeswalker23
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "index.html", "/index"})
    public String index(){
        return "index";
    }

    /**
     * 后台管理-文章审核页面
     * @param comment
     * @param model
     * @return
     */
    @GetMapping("/manager")
    public String manager(Comment comment, PageMessage pageMessage, Model model){
        try {
            SessionUtil.checkRootAuthority();
        } catch (NotLoginException e) {
            log.error(e.getMessage(), e);
            return "redirect:/index#loginForm";
        }
        Response<PageInfo<Comment>> res = commentController.getAllComments(comment, pageMessage);
        model.addAttribute("pageInfo", res.getData());
        return "manager";
    }

    /**
     * 后台管理-发布文章页面
     * @return
     */
    @GetMapping("/addDoc")
    public String addDoc(){
        try {
            SessionUtil.checkRootAuthority();
        } catch (NotLoginException e) {
            log.error(e.getMessage(), e);
            return "redirect:/index#loginForm";
        }
        return "add-doc";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @Autowired
    private CommentController commentController;

    /**
     * 个人中心页面
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String profile(PageMessage pageMessage, Model model){
        try {
            model.addAttribute("user", SessionUtil.getUserBean());
            Response<PageInfo<Comment>> comments =  commentController.getMyLikeComment(pageMessage);
            model.addAttribute("pageInfo", comments.getData());
        } catch (NotLoginException e) {
            log.error(e.getMessage(), e);
            return "redirect:/index#loginForm";
        }
        return "contact-us";
    }
}
