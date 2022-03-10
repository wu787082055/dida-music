package com.padapp.pad.dto;

import com.baomidou.mybatisplus.annotation.TableField;

import javax.validation.constraints.NotBlank;

public class LoginDto {


    @NotBlank(message = "username is null")
    private String username;


    @NotBlank(message = "password is null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
