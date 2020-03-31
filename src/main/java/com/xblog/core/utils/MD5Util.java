package com.xblog.core.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {


    // 不带秘钥加密
    public static String md5(String text) {
        // 加密后的字符串
        String md5str = DigestUtils.md5DigestAsHex(text.getBytes());
        return md5str;
    }


    /**
     * MD5验证方法
     * @param text
     * @param md5
     * @return
     */
    public static boolean verify(String text, String md5) {
        String md5str = md5(text);
        if (md5str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    // 测试
    public static void main(String[] args){
        String text = "123456";
        String s = md5(text);
        System.out.println(s);
        System.out.println(verify(text, s));
    }
}
