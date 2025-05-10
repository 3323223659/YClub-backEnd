package com.club.auth.common.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/0:36
 * @Description: 用户状态枚举
 */

public enum AuthUserStatusEnum {
    OPEN(0, "启用"),
    CLOSE(1, "禁用");

    private Integer code;
    private String desc;

    AuthUserStatusEnum(Integer code, String desc) {
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
