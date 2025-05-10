package com.club.auth.common.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/01/7:39
 * @Description: 分页参数
 */

@Data
public class PageInfo {

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    public Integer getPageNo(){
        if (pageNo == null ||  pageNo < 1){
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize(){
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE){
            return 20;
        }
        return pageSize;
    }

}
