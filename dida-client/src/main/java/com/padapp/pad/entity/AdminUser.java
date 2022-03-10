package com.padapp.pad.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@TableName("admin_user")//对应表名
//用mybaits plus记得连接serializable接口,点击黄色的类名，添加uid

public class AdminUser implements Serializable {

    private static final long serialVersionUID = -3712426180920984931L;

    @TableId
    private String id;

    @TableField("username")
    @NotBlank(message = "username is null")
    private String username;

    @TableField("password")
    @NotBlank(message = "password is null")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "AdminUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

