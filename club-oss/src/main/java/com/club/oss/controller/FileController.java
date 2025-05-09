package com.club.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.club.oss.service.FileService;
import com.club.oss.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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

    @RequestMapping("/uploadFile")
    public String getAllBucket() throws Exception {
        return fileService.getAllBucket().toString();
    }

}
