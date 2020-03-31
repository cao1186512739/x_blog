package com.xblog.mapper;


import com.xblog.core.model.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}