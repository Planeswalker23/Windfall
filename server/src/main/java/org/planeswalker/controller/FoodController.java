package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/food")
    public String food(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(3);
        PageInfo<Comment> comments = commentService.getComments(comment, pageMessage);
        model.addAttribute("comments",comments);
        return "food";
    }
}
