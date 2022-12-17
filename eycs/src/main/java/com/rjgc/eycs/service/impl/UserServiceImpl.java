package com.rjgc.eycs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.eycs.entity.User;
import com.rjgc.eycs.mapper.UserMapper;
import com.rjgc.eycs.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
