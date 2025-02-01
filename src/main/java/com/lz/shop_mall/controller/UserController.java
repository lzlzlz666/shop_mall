package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.pojo.User;
import com.lz.shop_mall.pojo.dto.UserDTO;
import com.lz.shop_mall.service.UserService;
import com.lz.shop_mall.util.JwtUtil;
import com.lz.shop_mall.util.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名或密码不能为空");
        }

        // 查询用户
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null) {
            // 没有占用
            userService.register(user.getUsername(), user.getPassword());
            return Result.success();
        } else {
            // 占用了
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();

        User loginUser = userService.findByUsername(username);
        // 判断用户是否存在
        if(loginUser == null){
            return Result.error("用户名错误");
        }

        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登录成功 --> 生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getUserId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    /*获取当前登录用户的信息*/
    @GetMapping
    public Result<UserDTO> loginUserList() {
        UserDTO userDTO = userService.loginUserList();
        return Result.success(userDTO);
    }

    @GetMapping("/{id}")
    public Result<UserDTO> getUserInfoById (@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/userInfo")
    public Result updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

}
