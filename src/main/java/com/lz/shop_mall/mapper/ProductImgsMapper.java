package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.ProductDetailImg;
import com.lz.shop_mall.pojo.ProductImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductImgsMapper {

    @Select("select * from product_imgs where product_id = #{id}")
    List<ProductImg> getProductImgs(Long id);

    @Select("select * from product_detail_img where product_id = #{id}")
    List<ProductDetailImg> getProductDetailImgs(Long id);
}
