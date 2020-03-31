package com.xblog.core.base;

import com.xblog.core.component.RedisComponent;
import com.xblog.core.model.constant.ResultCode;
import com.xblog.core.model.dto.JsonResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {

    @Autowired
    protected RedisComponent redisComponent;

    /**
     * 简单成功返回结果
     * @return
     */
    public JsonResultDto success(){
        return JsonResultDto.init(ResultCode.SUCCESS, null);
    }

    /**
     * 自定义返回消息
     * @param msg 提示信息
     * @return
     */
    public JsonResultDto success(String msg){
        return JsonResultDto.init(ResultCode.SUCCESS.getCode(), msg, null);
    }

    /**
     * 返回数据
     * @param data 数据
     * @return
     */
    public <T> JsonResultDto<T> success(T data){
        return JsonResultDto.init(ResultCode.SUCCESS, data);
    }

    /**
     * 简单失败返回结果
     * @return
     */
    public JsonResultDto failed(){
        return JsonResultDto.init(ResultCode.FAILED, null);
    }
    /**
     * 自定义失败返回消息
     * @param msg 提示信息
     * @return
     */
    public JsonResultDto failed(String msg){
        return JsonResultDto.init(ResultCode.FAILED.getCode(), msg, null);
    }

    /**
     * 自定义失败返回消息
     * @param code 状态码
     * @param msg 提示信息
     * @return
     */
    public JsonResultDto failed(int code, String msg){
        return JsonResultDto.init(code, msg, null);
    }

    /**
     * 自定义失败返回消息
     * @param resultCode 状态码与提示信息
     * @return
     */
    public JsonResultDto failed(ResultCode resultCode){
        return JsonResultDto.init(resultCode.getCode(), resultCode.getMsg(), null);
    }
}
