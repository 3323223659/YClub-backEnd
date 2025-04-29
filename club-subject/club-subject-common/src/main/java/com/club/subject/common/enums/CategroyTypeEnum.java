package com.club.subject.common.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/0:50
 * @Description:
 */

public enum CategroyTypeEnum {
    PRIMARY(1,"岗位大类"),
    SECONDARY(2,"二级分类");

    private Integer code;
    private String desc;

    CategroyTypeEnum(Integer code, String desc) {
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

    public void setDesc(String desc) {
    }

}
