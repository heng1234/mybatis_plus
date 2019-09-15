package com.hlvy.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hlvy.mybatis_plus.entity.User;
import com.hlvy.mybatis_plus.mapper.UserMapper;
import com.hlvy.mybatis_plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 * 通用service
 * @author heng
 * @date 2019/9/13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
