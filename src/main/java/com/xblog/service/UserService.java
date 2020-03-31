package com.xblog.service;

import com.github.pagehelper.PageInfo;
import com.xblog.core.model.dto.InputUserDto;
import com.xblog.core.model.dto.JsonResultDto;
import com.xblog.core.model.dto.OutputUserDto;
import com.xblog.core.model.po.Menu;
import com.xblog.core.model.po.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    JsonResultDto<PageInfo<User>> get(Integer page, Integer limit);

    JsonResultDto add(InputUserDto inputUserDto);

    JsonResultDto edit(InputUserDto inputUserDto);

    OutputUserDto getByUsername(String username);

    List<Menu> getMenus(String username);

    JsonResultDto login(HttpServletResponse response, String username, String password);
}
