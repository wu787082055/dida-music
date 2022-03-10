package com.padapp.pad.controller;

import com.padapp.pad.e.ResultDto;
import com.padapp.pad.entity.Company;
import com.padapp.pad.mapper.CompanyMapper;
import com.padapp.pad.server.CompanyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("api")
@RestController
@CrossOrigin
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @GetMapping("company")
    public ResultDto getAllCompany(){
        return ResultDto.success(companyService.getlist());
    }

    @PostMapping("company")
    public ResultDto addCompany(@Validated @RequestBody Company company){
        return ResultDto.success(companyService.addCompany(company));
    }

    @PutMapping("company")
    public ResultDto updateCompany(@Validated @RequestBody Company company){
        return ResultDto.success(companyService.updateCompany(company));
    }

    @DeleteMapping("company/{id}")
    public ResultDto deleteCompany(@PathVariable String id){
        companyService.deleteCompany(id);
        return ResultDto.success();
    }


}
