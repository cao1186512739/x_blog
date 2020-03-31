package com.xblog.controller;

import com.github.pagehelper.PageInfo;
import com.xblog.core.base.BaseService;
import com.xblog.core.model.constant.ResultCode;
import com.xblog.core.model.dto.InputUserDto;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.model.po.User;
import com.xblog.core.utils.StringUtil;
import com.xblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description: 用户api接口映射层
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/

@Api(value = "user-api", tags = "用户api接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseService {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页查询用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",  value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "行数", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/list")
    public JsonResultDto<PageInfo<User>> getUserListPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        if (StringUtil.isNull(page) && StringUtil.isNull(limit)){
            return failed("分页参数为空");
        }
        return userService.get(page,limit);
    }

    public JsonResultDto addUser(@Validated @RequestBody InputUserDto inputUserDto){
        // 参数校验
        if (StringUtil.isNull(inputUserDto)){
            failed(ResultCode.PARAM_ERROR);
        }
        return userService.add(inputUserDto);
    }
}
