package org.planeswalker.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Errors;
import org.planeswalker.exception.CommentException;
import org.planeswalker.exception.NotLoginException;
import org.planeswalker.mapper.CommentMapper;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.TypeNum;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.utils.NumberUtil;
import org.planeswalker.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 帖子模块服务层
 * @author Planeswalker23
 * @date Created in 2020-02-04
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 新增 comment
     * @param comment
     * @return commentId
     */
    public String addComment(Comment comment) {
        // 参数校验 userId, content
        if (StringUtils.isEmpty(comment.getUserId()) || StringUtils.isEmpty(comment.getContent())) {
            throw new CommentException(Errors.EMPTY_PARAMS);
        }
        // 判断 userId 是否存在
        loginService.getUserByUserId(comment.getUserId());
        comment.setCommentId(NumberUtil.createUuId());
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
        return comment.getCommentId();
    }

    /**
     * 根据 userId 和 commentId 删除 comment
     * @param userId
     * @param commentId
     * @return {@link Integer}
     */
    public Integer deleteComment(String userId, String commentId) {
        // 参数校验 userId, commentId
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(commentId)) {
            throw new CommentException(Errors.EMPTY_PARAMS);
        }
        // 权限及数据校验
        this.checkAndGetCommentByUserIdAndCommentId(userId, commentId);
        return commentMapper.deleteById(commentId);
    }

    /**
     * 修改 comment，不允许修改点赞数及 commentPid
     * @param comment
     * @return
     */
    public Integer updateComment(Comment comment) {
        // 权限及数据校验
        this.checkAndGetCommentByUserIdAndCommentId(comment.getUserId(), comment.getCommentId());
        return commentMapper.updateById(comment);
    }


    /**
     * 根绝 userId 和 commentId 查询 comment
     * @param userId
     * @param commentId
     * @throws CommentException 参数校验失败，传入参数为空
     *                          根据 commentId 查询数据为空;
     *                          查询得到的 comment 的作者不是传入的参数 userId，没有操作权限
     * @throws org.planeswalker.exception.LoginException 该用户不存在
     * @return
     */
    private Comment checkAndGetCommentByUserIdAndCommentId(String userId, String commentId) {
        // 参数校验 userId, commentId
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(commentId)) {
            throw new CommentException(Errors.EMPTY_PARAMS);
        }
        // 判断 userId 是否存在
        User user = loginService.getUserByUserId(userId);
        Comment comment = commentMapper.selectById(commentId);
        // 根据 commentId 查询数据为空，或不是管理员且该 comment 为启用
        if (comment == null
                || (Constant.ONE.equals(user.getAuthority()) && Constant.ZERO.equals(comment.getState()))) {
            throw new CommentException(Errors.DATA_NOT_EXIST);
        }
        return comment;
    }

    /**
     * 根据 commentId 查询 comment
     * @param commentId
     * @return {@link Comment}
     */
    public Comment getOneComment(String commentId) {
        // 参数校验 commentId
        if (StringUtils.isEmpty(commentId)) {
            throw new CommentException(Errors.EMPTY_PARAMS);
        }
        Comment comment = commentMapper.selectById(commentId);
        if (comment == null || Constant.ZERO.equals(comment.getState())) {
            throw new CommentException(Errors.DATA_NOT_EXIST);
        }
        //comment.setLikeNum(this.getZanByLikeNum(comment.getLikeNum()).toString());
        return comment;
    }

    /**
     * 根据 userId 查询我的所有 comments （分页）
     * @param comment
     * @param pageMessage
     * @return
     */
    public PageInfo<Comment> getComments(Comment comment, PageMessage pageMessage) {
        // 设置分页信息
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        try {
            User loginUser = SessionUtil.getUserBean();
            if (!Constant.ZERO.equals(loginUser.getAuthority())) {
                comment.setState(Constant.ONE);
            }
        } catch (NotLoginException e) {
            log.info("用户未登录，需要查询启用的 comment");
            comment.setState(Constant.ONE);
        }
        List<Comment> comments = commentMapper.selectList(Wrappers.lambdaQuery(comment));
        // 查询 userName，使用 hashSet 是为了去重
        Set<String> userIds = new HashSet<>(Constant.TEN);
        comments.forEach(comment1 -> userIds.add(comment1.getUserId()));
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getUserId, userIds));
        Map<String, String> userId2NameMap = new HashMap<>(Constant.TEN);
        users.forEach(user -> userId2NameMap.put(user.getUserId(), user.getUserName()));
        // 添加 userName 和 likeNum 返回
        comments.forEach(comment1 -> {
            comment1.setUserName(userId2NameMap.get(comment1.getUserId()));
            comment1.setLikeNum(this.getZanByLikeNum(comment1.getLikeNum()).toString());
        });
        return new PageInfo<>(comments);
    }


    /**
     * 点赞 comment，若已点赞则取消
     * @param userId
     * @param commentId
     * @return 该 comment 的点赞数
     */
    public Integer likeOrCancelComment(String userId, String commentId) {
        // 根据 commentId 查询 comment
        Comment comment = this.checkAndGetCommentByUserIdAndCommentId(userId, commentId);
        String likeNum = comment.getLikeNum();
        Integer num;
        // 当前没有人点赞，记录该 userId 后返回
        if (StringUtils.isEmpty(likeNum)) {
            likeNum = userId;
            num = Constant.ONE;
        } else {
            // 有点赞记录，若存在 userId，删除；若不存在则添加userId
            List<String> userIds = new ArrayList<>(Constant.TEN);
            // 使用 Arrays.toList() 会报 UnsupportedOperationException
            String[] userIdStrings = likeNum.split(Constant.DOU_HAO);
            for (String string : userIdStrings) {
                userIds.add(string);
            }
            if (userIds.contains(userId)) {
                userIds.remove(userId);
            } else {
                userIds.add(userId);
            }
            // 以 Constant.DOU_HAO 为分隔符，将 list 转化为 String
            likeNum = StringUtils.collectionToDelimitedString(userIds, Constant.DOU_HAO);
            num = userIds.size();
        }
        // 更新 likeNum
        Comment updateComment = new Comment();
        updateComment.setCommentId(commentId);
        updateComment.setLikeNum(likeNum);
        commentMapper.updateById(updateComment);
        return num;
    }

    /**
     * 获取我点赞的帖子
     * @param pageMessage
     * @return
     */
    public PageInfo<Comment> getMyLikeComment(PageMessage pageMessage) {
        // 设置分页信息
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        List<Comment> comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getState, Constant.ONE)
                .like(Comment::getLikeNum, SessionUtil.getUserId()));
        comments.forEach(comment1 -> comment1.setLikeNum(this.getZanByLikeNum(comment1.getLikeNum()).toString()));
        return new PageInfo<>(comments);
    }

    /**
     * 计算点赞数
     * @param likeNum
     * @return
     */
    private Integer getZanByLikeNum(String likeNum) {
        // 当前没有人点赞，记录该 userId 后返回
        if (StringUtils.isEmpty(likeNum)) {
            return Constant.ZERO;
        } else {
            return likeNum.split(Constant.DOU_HAO).length;
        }
    }

    /**
     * 修改 comment 的启用状态
     * @param state
     * @param commentId
     */
    public void openOrCloseComment(Integer state, String commentId) {
        User user = SessionUtil.getUserBean();
        if (!Constant.ZERO.equals(user.getAuthority())) {
            throw new CommentException("没有编辑权限");
        }
        this.checkAndGetCommentByUserIdAndCommentId(user.getUserId(), commentId);
        Comment comment = new Comment();
        comment.setState(state);
        comment.setCommentId(commentId);
        commentMapper.updateById(comment);
    }

    /**
     * 模糊搜索标题
     * @param comment
     * @param pageMessage
     * @return
     */
    public PageInfo<Comment> searchLikelyTitle(Comment comment, PageMessage pageMessage) {
        // 设置分页信息
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        try {
            User loginUser = SessionUtil.getUserBean();
            if (!Constant.ZERO.equals(loginUser.getAuthority())) {
                comment.setState(Constant.ONE);
            }
        } catch (NotLoginException e) {
            log.info("用户未登录，需要查询启用的 comment");
            comment.setState(Constant.ONE);
        }
        List<Comment> comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getType, comment.getType())
                .like(Comment::getTitle, comment.getTitle()));
        // 查询 userName，使用 hashSet 是为了去重
        Set<String> userIds = new HashSet<>(Constant.TEN);
        comments.forEach(comment1 -> userIds.add(comment1.getUserId()));
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getUserId, userIds));
        Map<String, String> userId2NameMap = new HashMap<>(Constant.TEN);
        users.forEach(user -> userId2NameMap.put(user.getUserId(), user.getUserName()));
        // 添加 userName 和 likeNum 返回
        comments.forEach(comment1 -> {
            comment1.setUserName(userId2NameMap.get(comment1.getUserId()));
            comment1.setLikeNum(this.getZanByLikeNum(comment1.getLikeNum()).toString());
        });
        return new PageInfo<>(comments);
    }

    /**
     * 获取各种类文章数目
     * @return
     */
    public List<TypeNum> getTypeNum() {
        List<TypeNum> count = commentMapper.getTypeNum();
        if (CollectionUtils.isEmpty(count)) {
            count.add(new TypeNum(Constant.ONE));
            count.add(new TypeNum(Constant.TWO));
            count.add(new TypeNum(Constant.THREE));
            count.add(new TypeNum(Constant.FOUR));
        }
        return count;
    }
}
