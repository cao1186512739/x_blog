package com.xblog.core.common;

import com.xblog.core.exception.LoginException;
import com.xblog.core.model.constant.PublicConstant;
import com.xblog.core.utils.CookieUtil;
import com.xblog.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 唯一登入，这里用的是map
 * <p>
 * key ： 存放
 */
@Slf4j
public class OnlyAccess {

    /**
     * 先进来先出去
     * FIFO
     */
    private static Stack<Map<String, Object>> stack = new Stack();

    public static void push(HttpServletRequest request, HttpServletResponse response, String username) {
        if (StringUtil.isBlank(username)) {
            throw new LoginException(PublicConstant.INVALID_TOKEN);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        if (stack.contains(map)){

        }


      //  Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("cookie", CookieUtil.getValue(request, PublicConstant.LOGIN_IDENTITY_KEY));
      //  map.put("id", Min_Map);
       // stack.push(Max_Map);
    }


}
