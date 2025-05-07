package com.club.oss.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/03/1:37
 * @Description: 文件信息
 */

@Data
public class FileInfo {

    private String fileName;

    private Boolean directoryFlag;

    private String etag;

}
