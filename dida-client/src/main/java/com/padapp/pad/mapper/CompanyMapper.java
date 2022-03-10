package com.padapp.pad.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.padapp.pad.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    //获取company表所有company
    @Select("SELECT * FROM company")
    List<Company> getAll();


}
