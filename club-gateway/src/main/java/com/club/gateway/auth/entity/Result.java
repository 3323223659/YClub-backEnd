package com.club.gateway.auth.entity;

import com.club.gateway.auth.enums.ResultCodeEnum;
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

    public static Result fail(int code){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(ResultCodeEnum.getByCode(code).getDesc());
        return result;
    }

}
