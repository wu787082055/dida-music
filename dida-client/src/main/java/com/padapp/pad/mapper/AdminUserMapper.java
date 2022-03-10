package com.padapp.pad.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.padapp.pad.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    /*@Select("select * from admin_user where id={id}")
    public AdminUser getAdminById(String id);*/

    @Select("SELECT * FROM admin_user")
    List<AdminUser> getAll();
}
