package com.gaop.appjob.common;


import java.io.Serializable;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 10:54
 * @description: ResultTool 返回结果的包装类
 */
public class ResultTool implements Serializable {


    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static JsonResult success(ResultCode resultEnum) {
        return new JsonResult(true,resultEnum);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static <T> JsonResult<T> success(ResultCode resultEnum,T data){
        return new JsonResult<>(true,resultEnum,data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
