package com.lz.shop_mall.controller;

import com.lz.shop_mall.pojo.Result;
import com.lz.shop_mall.util.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();

        // 保证文件名唯一
        String filename = UUID.randomUUID().toString()
                +originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("D:\\java_all_codes\\bigEvent\\filesTest\\" + filename));
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }

}
