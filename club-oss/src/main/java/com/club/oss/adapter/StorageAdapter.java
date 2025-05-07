package com.club.oss.adapter;

import com.club.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/2:21
 * @Description: 存储服务接口供minio、阿里云、腾讯云各种云存储服务实现
 */

public interface StorageAdapter {

    /**
     * 创建存储桶（如果不存在）
     *
     * @param bucket 存储桶名称
     * @throws Exception 操作失败时抛出异常
     */
    public void createBucket(String bucket);

    /**
     * 上传文件
     *
     * @param bucket      存储桶名称
     * @param objectName  文件名称
     * @param file 文件
     * @throws Exception 操作失败时抛出异常
     */
    public void uploadFile(String bucket, String objectName,  MultipartFile file);

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket();

    /**
     * 列出桶内以及文件
     */
    public List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     */
    public InputStream downloadFile(String bucket, String objectName);

    /**
     * 删除文件
     */
    public void deleteFile(String bucket, String objectName);

    /**
     * 删除桶
     */
    public void deleteBucket(String bucket);

}
