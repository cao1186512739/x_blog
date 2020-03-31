package com.xblog.core.model.dto;

import com.xblog.core.model.constant.ResultCode;

import java.io.Serializable;

/**
 * @ClassName JsonResultDto
 * @Description: 这里采用单例模式封装统一结果响应类
 * @Author hungkuei
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class JsonResultDto<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private JsonResultDto() {}

    private JsonResultDto(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResultDto<T> init(ResultCode resultCode, T data){
        return init(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> JsonResultDto<T> init(int code, String msg, T data){
        return new JsonResultDto<>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
