package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Banner;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public Result<List<Banner>> getList() {
        return bannerService.getList();
    }
}
