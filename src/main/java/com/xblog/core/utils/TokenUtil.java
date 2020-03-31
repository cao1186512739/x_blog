package com.xblog.core.utils;


import java.math.BigInteger;

public class TokenUtil {

    /**
     * 生成token令牌
     * @param username
     * @return
     */
    public static String makeToken(String username){
        String str = JacksonUtil.writeValueAsString(username);
        return new BigInteger(str.getBytes()).toString(16);
    }

    /**
     * 解析token令牌
     * @param token
     * @return
     */
    public static String parseToken(String token){
        if (StringUtil.isBlank(token)){
            return token;
        }
        String str = new String(new BigInteger(token, 16).toByteArray());
        return JacksonUtil.readValue(str, String.class);
    }

    public static void main(String[] args){

        String name = "cc";
        String token = makeToken(name);
        String username = parseToken(token);
        System.out.println(name);
        System.out.println(token);
        System.out.println(username);
    }
}
