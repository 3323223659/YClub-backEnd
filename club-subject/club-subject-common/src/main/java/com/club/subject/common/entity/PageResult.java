package com.club.subject.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/01/7:41
 * @Description: 分页返回实体
 */

@Data
public class PageResult<T> implements Serializable {

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private int total = 0;

    private Integer totalPages = 0;

    private List<T> result = Collections.emptyList();

    private Integer start = 1;

    private Integer end = 0;

    public void setRecords(List<T> records){
        this.result = records;
    }

    public void setTotal(int total) {
        this.total = total;
        if (this.pageSize > 0){
            this.totalPages = total % this.pageSize == 0 ? total / this.pageSize : total / this.pageSize + 1;
        }else {
            this.totalPages = 0;
        }
        this.start = this.pageNo > 0 ? ((this.pageNo - 1) * this.pageSize + 1) : 1;
        this.end = this.start + this.pageSize - 1;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo != null && pageNo > 0){
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0){
            this.pageSize = pageSize;
        }
    }

}
