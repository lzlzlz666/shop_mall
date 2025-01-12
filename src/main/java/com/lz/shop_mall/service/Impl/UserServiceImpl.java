package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.UserMapper;
import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.service.UserService;
import com.lz.shop_mall.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据username 查找对应的user
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    /**
     * 实现注册功能
     * @param username
     * @param password
     */
    public void register(String username, String password) {
        // 加密
        String md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username, md5String);
    }
}
