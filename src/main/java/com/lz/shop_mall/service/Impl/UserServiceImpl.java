package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.UserMapper;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.pojo.dto.UserDTO;
import com.lz.shop_mall.service.UserService;
import com.lz.shop_mall.util.Md5Util;
import com.lz.shop_mall.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    /**
     * 获取当前登录用户的个人信息
     * @return
     */
    public UserDTO loginUserList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return userMapper.getUserLogin(id);
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public Result updateUserInfo(User user) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        userMapper.update(user, userId);
        return Result.success();
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    /**
     * 根据id 查询数据库中user的信息
     * @param id
     * @return
     */
    public Result<UserDTO> getUserById(Integer id) {
        UserDTO userDTO = userMapper.getUserLogin(id);
        return Result.success(userDTO);
    }
}
