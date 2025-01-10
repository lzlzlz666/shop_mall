package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Category;
import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<List<Category>> getCategories() {
        List<Category> lc = categoryService.getCategories();

        return Result.success(lc);
    }
}
