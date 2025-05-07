package com.club.oss.adapter;

import com.club.oss.entity.FileInfo;
import com.club.oss.adapter.StorageAdapter;
import com.club.oss.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
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

    @Override
    @SneakyThrows
    public void createBucket(String bucket) {
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(String bucket, String objectName, MultipartFile file) {
        minioUtil.createBucket(bucket);
        if (objectName == null || objectName.isEmpty()) {
            minioUtil.uploadFile(bucket, file.getName(), file.getInputStream());
        }else {
            minioUtil.uploadFile(bucket, objectName+ '/' +file.getName(), file.getInputStream());
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
    public InputStream downloadFile(String bucket, String objectName) {
        return minioUtil.downloadFile(bucket, objectName);
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
