package com.lz.shop_mall.service;

import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.pojo.dto.UserDTO;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    UserDTO loginUserList();
}
