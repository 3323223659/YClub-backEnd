package com.club.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.club.oss.service.FileService;
import com.club.oss.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/1:54
 * @Description: 文件控制器
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/getAllBucket")
    public String getAllBucket() throws Exception {
        return fileService.getAllBucket().toString();
    }

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    @RequestMapping("/upload")
    public String upload(String bucket, String objectName, MultipartFile file) throws Exception {
        return fileService.upload(bucket, objectName, file);
    }

    @RequestMapping("/downloadFile")
    public String downloadFile(String bucket, String objectName) throws Exception {
        return fileService.downloadFile(bucket, objectName);
    }

}
