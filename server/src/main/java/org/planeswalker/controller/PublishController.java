package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.CommentService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class PublishController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/publish")
    public String publish(Model model){
        try {
            model.addAttribute("user", SessionUtil.getUserBean());
        } catch (NotLoginException e) {
            log.error(e.getMessage(), e);
            return "redirect:/index#loginForm";
    }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                          @RequestParam(value = "content",required = false) String content,
                          @RequestParam(value = "id",required = false) String id,
                          HttpServletRequest request,
                          Model model){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Comment comment=new Comment();
        comment.setCommentId(id);
        comment.setUserId(user.getUserId());
        comment.setTitle(title);
        comment.setContent(content);
        commentService.addComment(comment);
        return "publish";
    }

}
