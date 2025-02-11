package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDetailImg {
    private Integer productDetailImgId;
    private Integer productId;
    private String productDetailImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
