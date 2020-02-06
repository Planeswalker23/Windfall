package org.planeswalker.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.service.CommentService;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 帖子控制层
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
     * 新增帖子
     * @param comment
     * @return {@link Response} commentId
     */
    @PostMapping("/add")
    public Response<String> addComment(@Valid Comment comment) {
        comment.setUserId(SessionUtil.getUserId());
        return Response.success(commentService.addComment(comment));
    }

    /**
     * 删除帖子
     * @param commentId
     * @return {@link Response} 删除的行数
     */
    @PostMapping("/delete")
    public Response<Integer> addComment(String commentId) {
        return Response.success(commentService.deleteComment(SessionUtil.getUserId(), commentId));
    }

    /**
     * 修改帖子
     * @param comment
     * @return {@link Response} 修改的行数
     */
    @PostMapping("/update")
    public Response<Integer> updateComment(@Valid Comment comment) {
        comment.setUserId(SessionUtil.getUserId());
        return Response.success(commentService.updateComment(comment));
    }

    /**
     * 查询单个帖子
     * @param commentId
     * @return {@link Response}
     */
    @GetMapping("/one")
    public Response<Comment> getOneComment(String commentId) {
        return Response.success(commentService.getOneComment(commentId));
    }

    /**
     * 查询我的所有帖子（分页）
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/my")
    public Response<PageInfo<Comment>> getMyComments(PageMessage pageMessage) {
        // Comment 构造器中有 userId 的参数校验
        return Response.success(commentService.getComments(new Comment(SessionUtil.getUserId()), pageMessage));
    }



    /**
     * 查询所有帖子（分页），可增加查询条件
     * @param comment
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/all")
    public Response<PageInfo<Comment>> getAllComments(Comment comment, PageMessage pageMessage) {
        return Response.success(commentService.getComments(comment, pageMessage));
    }

    /**
     * 点赞 comment，若已点赞则取消
     * @param commentId
     * @return {@link Response} 修改的行数
     */
    @PostMapping("/like")
    public Response<Integer> likeOrCancelComment(String commentId) {
        return Response.success(commentService.likeOrCancelComment(SessionUtil.getUserId(), commentId));
    }

    /**
     * 获取我点赞的帖子
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/myLike")
    public Response<PageInfo<Comment>> getMyLikeComment(PageMessage pageMessage) {
        return Response.success(commentService.getMyLikeComment(pageMessage));
    }

    /**
     * 启用或禁用帖子
     * @param state
     * @param commentId
     * @return {@link Response}
     */
    @PostMapping("/state")
    public Response getMyLikeComment(Integer state, String commentId) {
        commentService.openOrCloseComment(state, commentId);
        return Response.success();
    }
}
