package com.padapp.pad.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.javassist.SerialVersionUID;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 8450898049217759797L;

    @TableId
    private String id;

    @TableField("company")
    @NotBlank(message = "company is null")
    private String company;

    @TableField("code")
    @NotBlank(message = "code is null")
    private String code;

    @TableField("create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
