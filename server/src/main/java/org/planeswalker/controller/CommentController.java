package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 评测、留言控制层
 * @author Planeswalker23
 * @date Created in 2020/2/4
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增评测/留言
     * 评测：commentPid=0 或为空
     * 留言：commentPid!=0 且不为空
     * @param comment
     * @return {@link Response} commentId
     */
    @PostMapping("/add")
    public Response<String> addComment(@Valid Comment comment) {
        return Response.success(commentService.addComment(comment));
    }

    /**
     * 删除评测/留言
     * @param userId
     * @param commentId
     * @return {@link Response} 删除的行数
     */
    @DeleteMapping("/delete")
    public Response<Integer> addComment(String userId, String commentId) {
        return Response.success(commentService.deleteComment(userId, commentId));
    }

    /**
     * 修改评测/留言，不允许修改点赞数及 commentPid
     * @param comment
     * @return {@link Response} 修改的行数
     */
    @PutMapping("/update")
    public Response<Integer> updateComment(Comment comment) {
        return Response.success(commentService.updateComment(comment));
    }

    /**
     * 查询单个评测/留言
     * @param commentId
     * @return {@link Response}
     */
    @GetMapping("/one")
    public Response<Comment> getOneComment(String commentId) {
        return Response.success(commentService.getOneComment(commentId));
    }

    /**
     * 查询我的所有评测/留言（分页）
     * @param userId
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/my")
    public Response<PageInfo<Comment>> getMyComments(String userId, PageMessage pageMessage) {
        // Comment 构造器中有 userId 的参数校验
        return Response.success(commentService.getComments(new Comment(userId), pageMessage));
    }

    /**
     * 查询所有评测/留言（分页）
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/all")
    public Response<PageInfo<Comment>> getAllComments(PageMessage pageMessage) {
        return Response.success(commentService.getComments(null, pageMessage));
    }

    /**
     * 点赞 comment，若已点赞则取消
     * @param userId
     * @param commentId
     * @return {@link Response} 修改的行数
     */
    @PutMapping("/like")
    public Response<Integer> likeOrCancelComment(String userId, String commentId) {
        return Response.success(commentService.likeOrCancelComment(userId, commentId));
    }
}
