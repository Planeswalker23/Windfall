package org.planeswalker.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.mapper.UserInfoMapper;
import org.planeswalker.mapper.UserMapper;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.dto.RootUserInfo;
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
    @GetMapping("/root/search")
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

    @PostMapping("/root/delete")
    public Response deleteUser(User user) {
        userMapper.deleteById(user.getUserId());
        userInfoMapper.deleteById(user.getUserId());
        return Response.success();
    }

    @PostMapping("/root/update")
    public Response update(RootUserInfo rootUserInfo) {
        User user = new User();
        BeanUtils.copyProperties(rootUserInfo, user);
        userMapper.updateById(user);

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(rootUserInfo, userInfo);
        userInfoMapper.updateById(userInfo);
        return Response.success();
    }

    @PostMapping("/root/add")
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

    @GetMapping("/root/goods")
    public Response<PageInfo<RootUserInfo>> getAllGoods(Goods goods, PageMessage pageMessage) {
        return null;
    }

    @GetMapping("/root/searchGoods")
    public Response<PageInfo<RootUserInfo>> searchGoods(String keyword, PageMessage pageMessage) {
        return null;
    }
}
