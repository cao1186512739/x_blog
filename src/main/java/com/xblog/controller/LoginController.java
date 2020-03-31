package com.xblog.controller;

import com.xblog.core.base.BaseService;
import com.xblog.core.model.constant.ResultCode;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.utils.StringUtil;
import com.xblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Description: 登录注册
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Api(value = "login-api", tags = "登录认证api接口模块")
@RestController
public class LoginController extends BaseService {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",  value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/login")
    public JsonResultDto login(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password){
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            // 参数检验失败
            return failed(ResultCode.PARAM_ERROR);
        }
        return userService.login(response, username, password);
    }


}
