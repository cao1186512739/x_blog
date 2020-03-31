package com.xblog.core.handler;

import com.xblog.core.base.BaseService;
import com.xblog.core.exception.AuthException;
import com.xblog.core.exception.LoginException;
import com.xblog.core.model.constant.ResultCode;
import com.xblog.core.model.dto.JsonResultDto;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseService {

    /**
     * 全局权限异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public JsonResultDto authExceptionHandler(AuthException e){
        return failed(ResultCode.FORBIDDEN.getCode(), e.getMessage());
    }


    /**
     * 全局登录异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(LoginException.class)
    public JsonResultDto loginExceptionHandler(LoginException e){
        return failed(ResultCode.UNAUTHORIZED.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResultDto methodNotSupported(HttpRequestMethodNotSupportedException e){
        return failed(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResultDto mappingNotFound(NoHandlerFoundException e){
        return failed(e.getMessage());
    }
}
