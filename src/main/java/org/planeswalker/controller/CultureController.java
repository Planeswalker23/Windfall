package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.TypeNum;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.CommentService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
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
        List<TypeNum> typeNums = commentService.getTypeNum();
        model.addAttribute("typeNums",typeNums);
        model.addAttribute("pageInfo",pageInfo);
        return "cultural";
    }

    /**
     * 列出所有风物文化信息
     * @return
     */
    @GetMapping("/natural")
    public String natural(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(Constant.FOUR);
        PageInfo<Comment> pageInfo = commentService.getComments(comment, pageMessage);
        List<TypeNum> typeNums = commentService.getTypeNum();
        model.addAttribute("typeNums",typeNums);
        model.addAttribute("pageInfo",pageInfo);
        return "natural";
    }

    /**
     * 搜索匹配标题的 comment
     * @param comment type必填，title必填
     * @return
     */
    @GetMapping("/search")
    public String natural(Comment comment, Model model, PageMessage pageMessage){
        PageInfo<Comment> pageInfo = commentService.searchLikelyTitle(comment, pageMessage);
        List<TypeNum> typeNums = commentService.getTypeNum();
        model.addAttribute("typeNums",typeNums);
        model.addAttribute("pageInfo",pageInfo);
        if (Constant.ONE.equals(comment.getType())) {
            return "cultural";
        } else if (Constant.FOUR.equals(comment.getType())) {
            return "natural";
        } else {
            return "redirect:/";
        }
    }

    /**
     * 查询单个文化信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/natural/{id}")
    public String naturalDetail(@PathVariable(name = "id") String id, Model model){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "natural-detail";
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
        } else {
            return "redirect:/index#loginForm";
        }
        return "redirect:/cultural/{id}";
    }

    /**
     * 风物文化-点赞
     * @return
     */
    @GetMapping("/natural/{id}/thumb")
    public String naturalThumb(@PathVariable(name = "id") String id){
        try {
            User user = SessionUtil.getUserBean();
            Integer likeNum = commentService.likeOrCancelComment(user.getUserId(), id);
            SessionUtil.addIntoSession("likeNum",likeNum);
        } catch (NotLoginException e) {
            log.error(e.getMessage(), e);
            return "redirect:/index#loginForm";
        }
        return "redirect:/natural/{id}";
    }

}
