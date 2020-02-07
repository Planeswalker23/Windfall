package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class NewsController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/news")
    public String news(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(2);
        PageInfo<Comment> comments = commentService.getComments(comment, pageMessage);
        model.addAttribute("pageInfo",comments);
        return "news";
    }
    /**
     * 查询单个文化信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/news/{id}")
    public String cultural_detail(@PathVariable(name = "id") String id,
                                  Model model,
                                  HttpServletRequest request){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "news-detail";
    }

    /**
     * 点赞
     * @param id
     * @param request
     * @param model
     * @return
     */

    @GetMapping("/news/{id}/thumb")
    public String thumb(@PathVariable(name = "id") String id,
                        HttpServletRequest request,
                        Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            Integer likeNum = commentService.likeOrCancelComment(user.getUserId(), id);
            session.setAttribute("likeNum",likeNum);
        } else {
            return "redirect:/index#loginForm";
        }
        return "redirect:/news/{id}";
    }
}
