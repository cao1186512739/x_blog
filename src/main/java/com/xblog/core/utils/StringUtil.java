package com.xblog.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 * @author caobing
 * @DateTime 2020/3/11 15:46
 */
public class StringUtil {

    /**
     *
     * 方法描述 如果字符串为空返回 true 否则返回false
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return !(str != null && !"".equals(str.trim()));
    }

    /**
     *
     * 方法描述 如果对象为空返回 true 否则返回false
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if(obj != null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "a j 25";
        System.out.println(isBlank(str));

        List<String> perms = new ArrayList<>();
        String uri = "/user/list";

        System.out.println(perms.contains(uri));
    }
}
