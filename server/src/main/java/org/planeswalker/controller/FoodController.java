package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import org.planeswalker.mapper.CommentMapper;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.EmailDto;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.service.CommentService;
import org.planeswalker.service.MailService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FoodController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    /**
     * 查询所有美食文章
     * @param model
     * @param pageMessage
     * @return
     */
    @GetMapping("/food")
    public String food(Model model, PageMessage pageMessage){
        Comment comment = new Comment();
        comment.setType(3);
        PageInfo<Comment> comments = commentService.getComments(comment, pageMessage);
        model.addAttribute("pageInfo",comments);
        return "food";
    }
    @GetMapping("/food/{id}")
    public String getfoodById(@PathVariable(name = "id") String id,
                              HttpServletRequest request,
                              Model model){
        Comment comment = commentService.getOneComment(id);
        model.addAttribute("comment",comment);
        return "food-detail";
    }

    @GetMapping("/food/{id}/thumb")
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
        return "redirect:/food/{id}";
    }

    @PostMapping("/send")
    public String sendEmail(EmailDto emailDto) {
        Comment comment = commentMapper.selectById(emailDto.getCommentId());
        if (comment == null) {
            return "redirect:/food/" + emailDto.getCommentId();
        }
        User accepter = userMapper.selectById(comment.getUserId());
        User sender = SessionUtil.getUserBean();
        if (accepter == null) {
            return "redirect:/food/" + emailDto.getCommentId();
        }
        emailDto.setAccepter(accepter.getEmail());
        emailDto.setSender(sender.getEmail());
        emailDto.setTitle(comment.getTitle());
        mailService.sendEmail(emailDto);
        return "redirect:/food/" + emailDto.getCommentId();
    }
}
