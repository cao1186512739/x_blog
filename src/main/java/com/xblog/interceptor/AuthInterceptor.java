package com.xblog.interceptor;

import com.xblog.core.exception.AuthException;
import com.xblog.core.exception.LoginException;
import com.xblog.core.model.constant.PublicConstant;
import com.xblog.core.model.dto.OutputUserDto;
import com.xblog.core.model.po.Menu;
import com.xblog.core.utils.CookieUtil;
import com.xblog.core.utils.StringUtil;
import com.xblog.core.utils.TokenUtil;
import com.xblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AuthInterceptor
 * @Description: 认证授权拦截器
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取Token令牌，检查用户是否登录
        String token = CookieUtil.getValue(request, PublicConstant.LOGIN_IDENTITY_KEY);
        if (StringUtil.isBlank(token)) {
            throw new LoginException(PublicConstant.UNAUTHO_ERROR);
        }
        // 解析token
        String username = TokenUtil.parseToken(token);
        OutputUserDto userDto = userService.getByUsername(username);
        if (StringUtil.isNull(userDto)){
            throw new LoginException(PublicConstant.INVALID_TOKEN);
        }
        // 权限校验
        if (!hasPermission(username, request.getRequestURI())) {
            throw new AuthException(PublicConstant.USER_NO_PERMITION);
        }
        return true;
    }

    // 判断该用户是否拥有此接口的访问权限
    private boolean hasPermission(String username, String uri){
        List<Menu> menus = userService.getMenus(username);
        List<String> perms = menus.stream().map(m -> m.getMenuPath()).collect(Collectors.toList());
        if (perms.contains(uri)) {
            return true;
        }
        return false;
    }
}
