package com.example.wkdly.controller;

import com.example.wkdly.entity.Result;
import com.example.wkdly.utils.JetUtil;
import com.example.wkdly.utils.OssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result <String>uploadFile(MultipartFile file)throws Exception {
        String originalFilename = file.getOriginalFilename();
        String suffix = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("C:\\Users\\25626\\Desktop\\"+suffix));
        String Url=OssUtil.uploadFile(suffix,file.getInputStream());
        return Result.success(Url);
    }
}
