package com.lz.shop_mall.pojo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Integer userId;
    private String username; // 用户名
    private String nickname; // 昵称
    private String email; // 邮箱
    private LocalDate birthday; // 生日
    private String phoneNumber; // 电话号码
    private String userPic; // 用户头像地址
    private Integer sex; // 性别
    private String address; // 地址
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
