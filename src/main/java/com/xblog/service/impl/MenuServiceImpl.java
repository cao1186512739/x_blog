package com.xblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xblog.core.base.BaseService;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.model.po.Menu;
import com.xblog.mapper.MenuMapper;
import com.xblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends BaseService implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public JsonResultDto<PageInfo<Menu>> get(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Menu> menus = menuMapper.selectAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menus);
        return success(pageInfo);
    }
}
