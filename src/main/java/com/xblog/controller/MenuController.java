package com.xblog.controller;

import com.github.pagehelper.PageInfo;
import com.xblog.core.base.BaseService;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.model.po.Menu;
import com.xblog.core.model.po.User;
import com.xblog.core.utils.StringUtil;
import com.xblog.mapper.MenuMapper;
import com.xblog.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MenuController
 * @Description: 菜单api接口映射层
 * @Author caobing
 * @Date 2020/3/25
 * @Version V1.0
 **/
@Api(value = "menu-api", tags = "菜单api接口")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseService {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "分页查询菜单列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",  value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "行数", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/list")
    public JsonResultDto<PageInfo<Menu>> getMenuListPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        if (StringUtil.isNull(page) && StringUtil.isNull(limit)){
            return failed("分页参数为空");
        }
        return menuService.get(page,limit);
    }

}
