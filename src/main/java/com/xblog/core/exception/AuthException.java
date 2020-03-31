package com.xblog.core.exception;

/**
 * @ClassName AuthException
 * @Description: 授权异常
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class AuthException extends RuntimeException{
    public AuthException(String message){
        super(message);
    }
}
