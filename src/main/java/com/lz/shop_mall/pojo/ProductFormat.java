package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductFormat {
    private Integer productFormatId;
    private Integer productId;
    private String productFormat;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
