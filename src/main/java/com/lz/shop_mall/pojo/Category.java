package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
