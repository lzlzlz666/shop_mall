package com.lz.shop_mall.pojo;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserAddress {

    private Integer userAddressId;
    private Integer userId; // 使用的用户
    private String receiverName; // 接受者姓名
    private String contact; // 联系方式
    private String address;
    private Integer defaultAddress;

    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
