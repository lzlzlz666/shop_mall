package com.lz.shop_mall.service.Impl;

import com.lz.shop_mall.mapper.BannerMapper;
import com.lz.shop_mall.pojo.Banner;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.service.BannerService;
import com.lz.shop_mall.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 获取所有的轮播图
     * @return
     */
    public Result<List<Banner>> getList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Banner> bannerList = bannerMapper.list(id);
        if( bannerList == null) {
            return Result.error("没有轮播图");
        }
        return Result.success(bannerList);
    }
}
