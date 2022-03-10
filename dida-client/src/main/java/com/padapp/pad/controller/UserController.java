package com.padapp.pad.controller;

import com.padapp.pad.config.JwtConfig;
import com.padapp.pad.e.ResultDto;
import com.padapp.pad.entity.User;
import com.padapp.pad.server.AdminUserService;
import com.padapp.pad.server.CompanyService;
import com.padapp.pad.server.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
//访问路径
@RequestMapping("api")
@RestController
@CrossOrigin
public class UserController {
    @Resource
    private JwtConfig jwtConfig;



    @Resource
    private UserService userService;

    @GetMapping("user")
    public ResultDto UsertestList(){
        return ResultDto.success(userService.getlst());

    }


    @PostMapping("user")
    public ResultDto Usertest(@Validated @RequestBody User user){

        return ResultDto.success(userService.addUser(user));

    }

    @PutMapping("user")
    //@Requestbody主要用于接收前端传给后端得json请求体（get无请求体，post有）
    public ResultDto UsertestEdit(@Validated @RequestBody User user){

        return ResultDto.success(userService.update(user));
    }

    @DeleteMapping("user/{id}")
    //@pathVariable 作用是传路径参数
    public ResultDto UsertestEdit(@PathVariable String id){

        userService.delete(id);
        return ResultDto.success();
    }


}
