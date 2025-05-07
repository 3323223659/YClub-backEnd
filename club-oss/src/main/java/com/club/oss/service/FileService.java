package com.club.oss.service;

import com.club.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;

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

}
