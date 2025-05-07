package com.club.oss.utils;
import com.club.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/1:17
 * @Description: minio文件操作工具类
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建存储桶（如果不存在）
     *
     * @param bucket 存储桶名称
     * @throws Exception 操作失败时抛出异常
     */
    public void createBucket(String bucket) throws Exception {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalArgumentException("桶名称不能为空");
        }
        // 检查存储桶是否存在
        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucket)
                        .build()
        );
        // 创建不存在的存储桶
        if (!exists) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucket)
                            .build()
            );
        }
    }

    /**
     * 上传文件
     *
     * @param bucket      存储桶名称
     * @param objectName  文件名称
     * @param inputStream 文件输入流
     * @throws Exception 操作失败时抛出异常
     */
    public void uploadFile(String bucket, String objectName, InputStream inputStream) throws Exception {
        if (objectName == null || objectName.isEmpty()) {
            throw new IllegalArgumentException("文件名称不能为空");
        }
        LocalDate today = LocalDate.now();
        String datePath = String.format("/%d/%02d/%02d/",
                today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        // 组合最终的对象路径: 年/月/日/文件名
        String fullObjectName = datePath + objectName;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fullObjectName)
                        .stream(inputStream, -1, 10485760)
                        .build()
        );
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() throws Exception {
        return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 列出桶内以及文件
     */
    public List<FileInfo> getAllFile(String bucket) throws Exception {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalArgumentException("桶不能为空");
        }
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).build());
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (Result<Item> result : results) {
            FileInfo fileInfo = new FileInfo();
            Item item = result.get();
            fileInfo.setFileName(item.objectName());
            fileInfo.setDirectoryFlag(item.isDir());
            fileInfo.setEtag(item.etag());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }

    /**
     * 下载文件
     */
    public InputStream downloadFile(String bucket, String objectName) throws Exception {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalArgumentException("桶不能为空");
        }
        if (objectName == null || objectName.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build();
        return minioClient.getObject(getObjectArgs);
    }

    /**
     * 删除文件
     */
    public void deleteFile(String bucket, String objectName) throws Exception {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalArgumentException("桶不能为空");
        }
        if (objectName == null || objectName.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build()
        );
    }

    /**
     * 删除桶
     */
    public void deleteBucket(String bucket) throws Exception {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalArgumentException("桶不能为空");
        }
        minioClient.removeBucket(
                RemoveBucketArgs.builder()
                        .bucket(bucket)
                        .build()
        );
    }
}
