package org.planeswalker.service;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户基本信息服务层
 * @author Planeswalker23
 * @date Created in 2019-11-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


}
