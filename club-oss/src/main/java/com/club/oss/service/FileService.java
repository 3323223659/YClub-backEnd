package com.club.oss.service;

import com.club.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/2:43
 * @Description: 判断文件存储服务并且与真正实现做交互
 */

@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    // 通过构造器注入，Spring会自动处理依赖
    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageAdapter.getAllBucket();
    };

    /**
     * 获取文件路径
     */
    public String getUrl(String bucketName, String objectName){
        LocalDate today = LocalDate.now();
        String datePath = String.format("/%d/%02d/%02d/",
                today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        // 组合最终的对象路径: 年/月/日/文件名
        objectName = datePath + objectName;
        return storageAdapter.getUrl(bucketName, objectName);
    };

    /**
     * 上传文件
     */
    public String upload(String bucket, String objectName, MultipartFile file){
        storageAdapter.uploadFile(bucket, objectName, file);
        if  (objectName == null || objectName.isEmpty()) {
            objectName = file.getOriginalFilename();
        }
        LocalDate today = LocalDate.now();
        String datePath = String.format("/%d/%02d/%02d/",
                today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        // 组合最终的对象路径: 年/月/日/文件名
        objectName = datePath + objectName;

        return storageAdapter.getUrl(bucket, objectName);
    };

    public String downloadFile(String bucket, String objectName) {

        return storageAdapter.downloadFile(bucket, objectName);
    }

}
