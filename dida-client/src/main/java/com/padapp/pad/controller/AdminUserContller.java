package com.padapp.pad.controller;

import com.padapp.pad.dto.EditAdminUserDto;
import com.padapp.pad.e.ResultDto;
import com.padapp.pad.entity.AdminUser;
import com.padapp.pad.server.AdminUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("api")
@RestController
@CrossOrigin
public class AdminUserContller {

    @Resource
    private AdminUserService adminUserService;

    //获取所有adminuser
    @GetMapping("admin_user")
    public ResultDto getAdminUser() {
        return ResultDto.success(adminUserService.getList());
    }

    //增加AdminUser
    @PostMapping("admin_user")
    //Validate用于验证表单
    public ResultDto addAminUser(@Validated @RequestBody AdminUser adminUser){
        return  ResultDto.success(adminUserService.addAdmin(adminUser));
    }

    //更新AdminUser信息,要id放入请求
    @PutMapping("admin_user")
    public ResultDto updateAdminUserById(@Validated @RequestBody EditAdminUserDto dto){
        return ResultDto.success(adminUserService.updateAdmin(dto));
    }

    //通过id删除AdminUser
    @DeleteMapping("admin_user/{id}")
    public ResultDto deleteAdminUserById(@PathVariable String id){
        adminUserService.deleteAdmin(id);
        return ResultDto.success();
    }
}
