package com.club.subject.common.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/29/0:36
 * @Description: 题目类型枚举
 */

public enum SubjectInfoTypeEnum {
    SUBJECT_TYPE_1(1, "单选"),
    SUBJECT_TYPE_2(2, "多选"),
    SUBJECT_TYPE_3(3, "判断"),
    SUBJECT_TYPE_4(4, "简答");

    private int code;
    private String desc;

    SubjectInfoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int code) {
        for (SubjectInfoTypeEnum subjectInfoTypeEnum : SubjectInfoTypeEnum.values()) {
            if (subjectInfoTypeEnum.code == code) {
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }
}
