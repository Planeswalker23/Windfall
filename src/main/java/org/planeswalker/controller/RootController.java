package org.planeswalker.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.mapper.CommentMapper;
import org.planeswalker.mapper.GoodsMapper;
import org.planeswalker.mapper.UserInfoMapper;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.RootCommentInfo;
import org.planeswalker.pojo.dto.RootGoodsInfo;
import org.planeswalker.pojo.dto.RootUserInfo;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.Goods;
import org.planeswalker.pojo.entity.User;
import org.planeswalker.pojo.entity.UserInfo;
import org.planeswalker.utils.NumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台管理系统-管理员接口
 * @author Planeswalker23
 * @date Created in 2020/2/8
 */
@Slf4j
@RestController
public class RootController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CommentController commentController;
    @Autowired
    private CommentMapper commentMapper;


    /**
     * 获取所有用户（分页）
     * @param user
     * @param pageMessage
     * @return
     */
    @GetMapping("/root/users")
    public Response<PageInfo<RootUserInfo>> getAllUsers(User user, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        // 查询 user 表信息
        List<User> userList = userMapper.selectList(Wrappers.lambdaQuery(user));
        // 查询 user_info 表信息
        List<UserInfo> userInfoList = userInfoMapper.selectBatchIds(Lists.transform(userList, User::getUserId));
        return Response.success(new PageInfo<>(this.bindData(userList, userInfoList)));
    }

    /**
     * 绑定数据
     * @param userList
     * @param userInfoList
     * @return
     */
    private List<RootUserInfo> bindData(List<User> userList, List<UserInfo> userInfoList) {
        Map<String, UserInfo> userInfoMap = Maps.uniqueIndex(userInfoList, UserInfo::getUserId);
        List<RootUserInfo> users = Lists.newArrayList();
        // 根据 userId 进行数据绑定
        for (int i=0; i<userList.size(); i++) {
            User userDTO = userList.get(i);
            RootUserInfo rootUserInfo = new RootUserInfo();
            rootUserInfo.setNo(i+1);
            // 拷贝 user 表信息
            BeanUtils.copyProperties(userDTO, rootUserInfo);
            UserInfo userInfo = userInfoMap.get(userDTO.getUserId());
            // 拷贝 user_info 表信息
            if (userInfo != null) {
                rootUserInfo.setFavourite(userInfo.getFavourite());
                rootUserInfo.setSignature(userInfo.getSignature());
            }
            users.add(rootUserInfo);
        }
        return users;
    }

    /**
     * 搜索 邮箱或昵称
     * @param keyword
     * @param pageMessage
     * @return
     */
    @GetMapping("/root/user/search")
    public Response<PageInfo<RootUserInfo>> searchUsers(String keyword, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        // 模糊匹配 user 的邮箱或昵称
        List<User> userList = userMapper.selectList(Wrappers.<User>lambdaQuery()
                .like(User::getEmail, keyword)
                .or().like(User::getUserName, keyword));
        // 查询 user_info 表信息
        List<UserInfo> userInfoList = userInfoMapper.selectBatchIds(Lists.transform(userList, User::getUserId));
        return Response.success(new PageInfo<>(this.bindData(userList, userInfoList)));
    }

    @PostMapping("/root/user/delete")
    public Response deleteUser(User user) {
        userMapper.deleteById(user.getUserId());
        userInfoMapper.deleteById(user.getUserId());
        return Response.success();
    }

    @PostMapping("/root/user/update")
    public Response update(RootUserInfo rootUserInfo) {
        User user = new User();
        BeanUtils.copyProperties(rootUserInfo, user);
        userMapper.updateById(user);

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(rootUserInfo, userInfo);
        userInfoMapper.updateById(userInfo);
        return Response.success();
    }

    @PostMapping("/root/user/add")
    public Response add(RootUserInfo rootUserInfo) {
        rootUserInfo.setUserId(NumberUtil.createUuId());
        rootUserInfo.setCreateTime(new Date());

        User user = new User();
        BeanUtils.copyProperties(rootUserInfo, user);
        userMapper.insert(user);

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(rootUserInfo, userInfo);
        userInfoMapper.insert(userInfo);
        return Response.success();
    }


    /**
     * 查询所有商品（分页）
     * @param goods
     * @param pageMessage
     * @return
     */
    @GetMapping("/root/goods")
    public Response<PageInfo<RootGoodsInfo>> getAllGoods(Goods goods, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        // 查询 user 表信息
        List<Goods> goodsList = goodsMapper.selectList(Wrappers.lambdaQuery(goods));
        return Response.success(new PageInfo<>(this.bindGoods(goodsList)));
    }

    @GetMapping("/root/goods/search")
    public Response<PageInfo<RootGoodsInfo>> searchGoods(String searchType, String keyword, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        // 模糊匹配 goods 的类型、名称、品牌、型号、需求
        List<Goods> goodsList = goodsMapper.selectList(Wrappers.<Goods>lambdaQuery()
                .eq(Goods::getType, searchType)
                .like(Goods::getGoodsName, keyword)
                .or().like(Goods::getBrand, keyword)
                .or().like(Goods::getType, keyword)
                .or().like(Goods::getSet, keyword)
                .or().like(Goods::getRequirement, keyword));
        return Response.success(new PageInfo<>(this.bindGoods(goodsList)));
    }

    private List<RootGoodsInfo> bindGoods(List<Goods> goodsList) {
        List<RootGoodsInfo> rootGoodsInfoList = Lists.newArrayList();
        for (int i = 0; i < goodsList.size(); i++) {
            Goods goodsDto = goodsList.get(i);
            RootGoodsInfo rootGoodsInfo = new RootGoodsInfo();
            BeanUtils.copyProperties(goodsDto, rootGoodsInfo);
            // 给序号赋值
            rootGoodsInfo.setNo(i+1);
            rootGoodsInfoList.add(rootGoodsInfo);
        }
        return rootGoodsInfoList;
    }


    @PostMapping("/root/goods/delete")
    public Response deleteGoods(Goods goods) {
        goodsMapper.deleteById(goods.getGoodsId());
        return Response.success();
    }

    @PostMapping("/root/goods/update")
    public Response updateGoods(Goods goods) {
        goodsMapper.updateById(goods);
        return Response.success();
    }

    @PostMapping("/root/goods/add")
    public Response addGoods(Goods goods) {
        goods.setGoodsId(NumberUtil.createUuId());
        goods.setCreateTime(new Date());
        goodsMapper.insert(goods);
        return Response.success();
    }

    /**
     * 获取所有评测
     * @param comment
     * @param pageMessage
     * @return
     */
    public PageInfo<RootCommentInfo> getAllComments(Comment comment, PageMessage pageMessage) {
        Response<PageInfo<Comment>> res = commentController.getAllPcComments(comment, pageMessage);
        return this.resPageInfo(res);
    }

    private PageInfo<RootCommentInfo> resPageInfo(Response<PageInfo<Comment>> res) {
        List<Comment> list = res.getRes().getList();
        List<RootCommentInfo> resList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            RootCommentInfo rootCommentInfo = new RootCommentInfo();
            BeanUtils.copyProperties(list.get(i), rootCommentInfo);
            rootCommentInfo.setNo(i+1);
            // 标题截取
            rootCommentInfo.setTitle(this.subString20(rootCommentInfo.getTitle()));
            // 内容截取
            rootCommentInfo.setContent(this.subString20(rootCommentInfo.getContent()));
            // 图片url
            rootCommentInfo.setImgUrl(this.subString20(rootCommentInfo.getImgUrl()));
            resList.add(rootCommentInfo);
        }
        PageInfo<RootCommentInfo> resPageInfo = new PageInfo<>();
        // 拷贝分页信息
        BeanUtils.copyProperties(res.getRes(), resPageInfo);
        resPageInfo.setList(resList);
        return resPageInfo;
    }

    private String subString20(String target) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        if (target.length()>20) {
            target = target.substring(0, 20) + "...";
        }
        return target;
    }

    public PageInfo<RootCommentInfo> searchComments(String keyword, Comment comment, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        Response<PageInfo<Comment>> res = commentController.searchAllPcComments(keyword, comment, pageMessage);
        return this.resPageInfo(res);
    }

    @PostMapping("/root/comment/delete")
    public Response deleteComment(Comment comment) {
        commentMapper.deleteById(comment.getCommentId());
        return Response.success();
    }

    @PostMapping("/root/comment/getAllChildCommentsByPid")
    public Response<List<Comment>> getAllChildCommentsByPid(Comment comment) {
        List<Comment> comments = commentMapper.selectList(Wrappers.lambdaQuery(comment));
        comments.forEach(comment1 -> {
            User user = userMapper.selectById(comment1.getUserId());
            comment1.setUserName(user.getUserName());
        });
        return Response.success(comments);
    }
}
