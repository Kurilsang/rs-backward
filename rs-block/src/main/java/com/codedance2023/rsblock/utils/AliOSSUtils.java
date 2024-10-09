package com.codedance2023.rsblock.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class AliOSSUtils {


    String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    String accessKeyId = "LTAI5t7ADKKHhLuT25jWThDx";

    String accessKeySecret = "QpMKIhvffiAAOfB7eHqHGdDKVQN9Eh";

    String bucketName = "web-newtest";

    //上传一个文件
    public String upload(MultipartFile file) throws IOException {

        //获取文件拓展名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String extraName = originalFilename.substring(index);

         //利用UUID生成随机文件名
        String fileName = UUID.randomUUID().toString() + extraName;

        //获取文件输入流
        InputStream inputStream = file.getInputStream();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileName,inputStream);

        //拼接返回的url
        String url = "https://" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        ossClient.shutdown();
        return url;
    }

    //上传多个文件(多图片或者多视频)
    public ArrayList<String> multiUpload(MultipartFile[] files) throws IOException {
        //声明存放多图片url集合
        ArrayList<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            //获取文件拓展名
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String extraName = originalFilename.substring(index);

            //利用UUID生成随机文件名
            String fileName = UUID.randomUUID().toString() + extraName;

            //获取文件输入流
            InputStream inputStream = file.getInputStream();
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);
            ossClient.putObject(bucketName,fileName,inputStream);

            //拼接返回的url
            String url = "https://" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
            ossClient.shutdown();
            //将这个图片的url加入集合中
            urls.add(url);
        }
        //返回整个集合
        return urls;
    }

}
