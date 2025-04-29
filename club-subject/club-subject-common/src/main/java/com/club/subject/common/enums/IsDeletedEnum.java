package com.club.subject.common.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/0:36
 * @Description: 删除状态枚举
 */

public enum IsDeletedEnum {
    DELETED(1, "已删除"),
    UN_DELETED(0, "未删除");

    private Integer code;
    private String desc;

    IsDeletedEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {}
}
