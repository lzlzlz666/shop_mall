package com.lz.shop_mall.mapper;

import com.lz.shop_mall.pojo.ProductFormat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductFormatMapper {

    @Select("select * from product_format where product_id = #{id}")
    List<ProductFormat> getProductFormatsById(Long id);
}
