package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer addressId;
    private Integer productId;
    private String productFormat;
    private Integer productCount;
    private Double productPrice;
    private Integer isPay;
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
