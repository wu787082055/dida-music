package com.padapp.pad.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.padapp.pad.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user")
    List<User> getAll();



}
