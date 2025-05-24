package com.club.oss.adapter;

import com.club.oss.entity.FileInfo;
import com.club.oss.adapter.StorageAdapter;
import com.club.oss.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/2:24
 * @Description: Minio存储服务实现类
 */

//@Service
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Value("${minio.url}")
    private String url;


    @Override
    @SneakyThrows
    public void createBucket(String bucket) {
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucketName, String objectName) {
//        LocalDate today = LocalDate.now();
//        String datePath = String.format("/%d/%02d/%02d/",
//                today.getYear(), today.getMonthValue(), today.getDayOfMonth());
//
//        // 组合最终的对象路径: 年/月/日/文件名
//        String fullObjectName = datePath + objectName;
//        return url + "/" + bucketName + fullObjectName;
        return minioUtil.getPreviewFileUrl(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(String bucket, String objectName, MultipartFile file) {
        minioUtil.createBucket(bucket);
        if (objectName == null || objectName.isEmpty()) {
            minioUtil.uploadFile(bucket, file.getOriginalFilename(), file.getInputStream());
        }else {
            minioUtil.uploadFile(bucket, objectName+ '/' +file.getOriginalFilename(), file.getInputStream());
        }

    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucket) {
        return minioUtil.getAllFile(bucket);
    }

    @Override
    @SneakyThrows
    public String downloadFile(String bucket, String objectName) {
         return url + minioUtil.downloadFile(bucket, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucket, String objectName) {
        minioUtil.deleteFile(bucket, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucket) {
        minioUtil.deleteBucket(bucket);
    }
}
