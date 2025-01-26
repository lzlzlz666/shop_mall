package com.lz.shop_mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Banner {
    private Integer bannerId;
    private String bannerImg;
    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
