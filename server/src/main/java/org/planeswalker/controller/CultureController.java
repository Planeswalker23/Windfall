package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.PaginationDTO;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CultureController {

    @Autowired
    private CommentService commentService;


    /**
     * 列出所有文化信息
     * @return
     */
    @GetMapping("/cultural")
    public String cultural(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(1);
        PageInfo<Comment> pageInfo = commentService.getComments(comment, pageMessage);
        model.addAttribute("pageInfo",pageInfo);
        return "cultural";
    }

    /**
     * 查询单个文化信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/cultural/{id}")
    public String cultural_detail(@PathVariable(name = "id") String id,
                                  Model model){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "cultural-detail";
    }

    /**
     * 古代遗迹
     * @return
     */
    @GetMapping("/ruins")
    public String ruins(){
        return "ruins";
    }

}
