package com.xblog.core.exception;

/**
 * @ClassName LoginException
 * @Description: 登录异常
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class LoginException extends RuntimeException {

    public LoginException(String message){
        super(message);
    }
}
