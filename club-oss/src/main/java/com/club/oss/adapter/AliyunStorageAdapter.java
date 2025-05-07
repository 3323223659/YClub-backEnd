package com.club.oss.adapter;

import com.club.oss.entity.FileInfo;
import com.club.oss.adapter.StorageAdapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/2:47
 * @Description: 阿里云存储服务实现类(这里只是为了彰显适配者模式)
 */

//@Service
public class AliyunStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public void uploadFile(String bucket, String objectName, MultipartFile file) {

    }

    @Override
    public List<String> getAllBucket() {
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testList.add("aliyunTest" + i);
        }
        return testList;
    }

    @Override
    public List<FileInfo> getAllFile(String bucket) {
        return null;
    }

    @Override
    public InputStream downloadFile(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteFile(String bucket, String objectName) {

    }

    @Override
    public void deleteBucket(String bucket) {

    }
}
