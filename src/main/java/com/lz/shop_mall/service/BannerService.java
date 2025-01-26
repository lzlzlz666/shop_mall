package com.lz.shop_mall.service;

import com.lz.shop_mall.pojo.Banner;
import com.lz.shop_mall.pojo.Result;

import java.util.List;

public interface BannerService {
    Result<List<Banner>> getList();
}
