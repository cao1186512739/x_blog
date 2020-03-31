package com.xblog.mapper;

import com.xblog.core.model.po.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectMenus(@Param("username") String username);
}