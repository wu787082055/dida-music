package com.padapp.pad.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 6055618693545095381L;
    @TableId
    private String id;

    @TableField("username")
    @NotBlank(message = "username is null")
    private String username;

    @TableField("people_code")
    @NotBlank(message = "peopleCode is null")
    private String peopleCode;

    @TableField("company_id")
    @NotBlank(message = "companyId is null")
    private String companyId;

    @TableField("company_code")
    @NotBlank(message = "companyCode is null")
    private String companyCode;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", peopleCode='" + peopleCode + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyCode='" + companyCode + '\'' +
                '}';
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

    public String getPeopleCode() {
        return peopleCode;
    }

    public void setPeopleCode(String peopleCode) {
        this.peopleCode = peopleCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
