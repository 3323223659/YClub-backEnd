package com.club.subject.common.entity;

import com.club.subject.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/04/27/0:52
 * @Description:
 */

@Data
public class Result<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }

}
