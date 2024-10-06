package com.kuril.logindemo.controller;
import com.kuril.logindemo.pojo.Result;
import com.kuril.logindemo.utils.MinioUtils;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    MinioUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.info("Upload File{}",file.getOriginalFilename());
        String url = aliOSSUtils.upload(file);
        log.info("文件的url为{}",url);
        return Result.success(url);
    }
}
