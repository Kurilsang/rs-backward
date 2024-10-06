package com.kuril.logindemo.utils;

import io.minio.*;
import io.minio.errors.*;
import lombok.Data;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Data
@Component
public class MinioUtils {
    private final Environment environment;

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = file.getInputStream();

//        String ip_addr="http://localhost:9000";
        String ip_addr=environment.getProperty("my.minio.ip_addr");
//        String bucketName="public";
        String bucketName=environment.getProperty("my.minio.bucketName");
//        String filePath="/images/head/";
        String filePath=environment.getProperty("my.minio.filePath");

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(ip_addr)
//                        .credentials("lS6LX3RcVtxyddKbsulR", "GcMlCStTashhwwGNfJQCP22AmXmoYa6bKi8mmZSW")
                        .credentials(environment.getProperty("my.minio.access-key"), environment.getProperty("my.minio.secret-key"))
                        .build();

//         看一下bucket是否存在
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("public").build());
        if (!found) {
            // 如果没有就创建一个bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("public").build());
        } else {
            System.out.println("Bucket 'public'存在了");
        }
//
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath+fileName)
                .stream(inputStream,file.getSize(),-1)
                .contentType(file.getContentType())
                .build());
        String url = ip_addr+"/"+bucketName+filePath+fileName;

        return url;// 把上传到oss的路径返回
    }

}
