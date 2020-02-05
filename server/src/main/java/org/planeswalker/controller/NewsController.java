package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/news")
    public String news(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(2);
        PageInfo<Comment> comments = commentService.getComments(comment, pageMessage);
        model.addAttribute("comments",comments);
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
                                  Model model){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "news-detail";
    }
}
