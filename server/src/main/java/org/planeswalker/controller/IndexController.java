package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.base.Response;
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

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
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
    public String profile(Model model){
        model.addAttribute("user", SessionUtil.getUserBean());
        Response<PageInfo<Comment>> comments =  commentController.getMyLikeComment(new PageMessage());
        model.addAttribute("comment", comments.getData().getList());
        return "contact-us";
    }
}
