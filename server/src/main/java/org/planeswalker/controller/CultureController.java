package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.PaginationDTO;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.CommentService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                                  Model model,
                                  HttpServletRequest request){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "cultural-detail";
    }

    /**
     * 点赞
     * @return
     */
    @GetMapping("/cultural/{id}/thumb")
    public String thumb(@PathVariable(name = "id") String id,
                        HttpServletRequest request,
                        Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            Integer likeNum = commentService.likeOrCancelComment(user.getUserId(), id);
            session.setAttribute("likeNum",likeNum);
        }
        return "redirect:/cultural/{id}";
    }

}
