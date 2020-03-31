package com.xblog.service;

import com.github.pagehelper.PageInfo;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.model.po.Menu;

public interface MenuService {

    JsonResultDto<PageInfo<Menu>> get(Integer page, Integer limit);
}
