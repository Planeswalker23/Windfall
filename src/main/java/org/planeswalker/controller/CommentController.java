package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @PostMapping("/delete")
    public Response<Integer> addComment(String userId, String commentId) {
        return Response.success(commentService.deleteComment(userId, commentId));
    }

    /**
     * 修改评测/留言，不允许修改点赞数及 commentPid
     * @param comment
     * @return {@link Response} 修改的行数
     */
    @PostMapping("/update")
    public Response<Integer> updateComment(Comment comment) {
        return Response.success(commentService.updateComment(comment));
    }

    /**
     * 查询单个评测/留言
     * @param userId
     * @param commentId
     * @return {@link Response}
     */
    @GetMapping("/one")
    public Response<Comment> getOneComment(String userId, String commentId) {
        return Response.success(commentService.getOneComment(userId, commentId));
    }

    /**
     * 查询我的所有评测（分页）
     * @param comment
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/myPc")
    public Response<PageInfo<Comment>> getMyPcComments(Comment comment, PageMessage pageMessage) {
        // Comment 构造器中有 userId 的参数校验
        comment.setCommentPid(Constant.ZERO.toString());
        return Response.success(commentService.getComments(comment, pageMessage));
    }

    /**
     * 查询所有评测（分页）
     * @param comment
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/allPc")
    public Response<PageInfo<Comment>> getAllPcComments(Comment comment, PageMessage pageMessage) {
        comment.setCommentPid(Constant.ZERO.toString());
        // 防止查询我的评测
        comment.setUserId(null);
        return Response.success(commentService.getComments(comment, pageMessage));
    }

    /**
     * 查询所有评测（分页）模糊搜索
     * @param keyword
     * @param comment
     * @param pageMessage
     * @return {@link Response}
     */
    public Response<PageInfo<Comment>> searchAllPcComments(String keyword, Comment comment, PageMessage pageMessage) {
        comment.setCommentPid(Constant.ZERO.toString());
        return Response.success(commentService.searchComments(keyword, comment, pageMessage));
    }

    /**
     * 查询该评测的所有留言（分页）
     * @param comment
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/allLy")
    public Response<PageInfo<Comment>> getAllLyComments(Comment comment, PageMessage pageMessage) {
        if (StringUtils.isEmpty(comment.getCommentId())) {
            return Response.failed(Errors.EMPTY_PARAMS);
        }
        // 防止查询我的评测
        comment.setUserId(null);
        // 将参数commentId设置到commentPid中，查询的是该commentId的留言
        comment.setCommentPid(comment.getCommentId());
        comment.setCommentId(null);
        return Response.success(commentService.getComments(comment, pageMessage));
    }

    /**
     * 点赞 comment，若已点赞则取消
     * @param userId
     * @param commentId
     * @return {@link Response} 修改的行数
     */
    @PostMapping("/like")
    public Response<Integer> likeOrCancelComment(String userId, String commentId) {
        return Response.success(commentService.likeOrCancelComment(userId, commentId));
    }
}
