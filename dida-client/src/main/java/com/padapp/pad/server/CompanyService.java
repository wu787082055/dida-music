package com.padapp.pad.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.padapp.pad.entity.Company;
import com.padapp.pad.exception.WebException;
import com.padapp.pad.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class CompanyService {
    @Resource
    private CompanyMapper companyMapper;

    public Object getlist(){
        //获取所有company
        List<Company> all = companyMapper.getAll();
        //创造querwrapper用于mybaits plus的内置方法
        QueryWrapper<Company> wrapper =new QueryWrapper<>();
        List<Company> companyList = companyMapper.selectList(wrapper);

        return companyList;

    }

    //加入Company
    public Company addCompany(Company company){
        //记得设置id,自动生成id getIdStr()
        company.setId(IdWorker.getIdStr());
        //日期自动生成
        company.setCreateTime(new Date());
        companyMapper.insert(company);
        return company;
    }

    public Company updateCompany(Company company){
        if (companyMapper.selectById(company.getId()) == null){
            throw new WebException("不存在该id的公司");
        }
            companyMapper.updateById(company);
            return company;
    }

    public void deleteCompany(String id){
        Company target = companyMapper.selectById(id);
        if (target == null){
            throw new WebException(id+"is null");
        }else {
            companyMapper.deleteById(id);
        }
    }
}
