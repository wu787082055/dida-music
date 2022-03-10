package com.padapp.pad.controller;

import com.padapp.pad.dto.LoginDto;
import com.padapp.pad.e.ResultDto;
import com.padapp.pad.server.AdminUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
@CrossOrigin
public class ServiceController {

    @Resource
    private AdminUserService adminUserService;

    @PostMapping("login")
    public ResultDto login(@Validated  @RequestBody LoginDto dto){

        return ResultDto.success(adminUserService.Login(dto));
    }



}
