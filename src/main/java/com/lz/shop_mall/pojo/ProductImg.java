package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductImg {
    private Integer productImgId;
    private Integer productId;
    private String productImg;
    private Integer defaultImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
