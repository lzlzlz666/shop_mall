package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("select * from banner")
    List<Banner> list();
}
