package com.padapp.pad.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.padapp.pad.config.JwtConfig;
import com.padapp.pad.dto.EditAdminUserDto;
import com.padapp.pad.dto.LoginDto;
import com.padapp.pad.entity.AdminUser;
import com.padapp.pad.exception.WebException;
import com.padapp.pad.mapper.AdminUserMapper;
import com.padapp.pad.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service

public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private JwtConfig jwtConfig;

    public Object getList(){
        List<AdminUser> all = adminUserMapper.getAll();
        //用于Mybaitsplus查询、如下面的selectList是自带的，需要用querywrapper
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();

        List<AdminUser> adminUsers = adminUserMapper.selectList(wrapper);

        return adminUsers;
    }

    //加入AdminUser**
    public AdminUser addAdmin(AdminUser adminUser){

        String pwd = adminUser.getPassword();
        //通过UUID进行数据自动生成
        String salt = UUID.randomUUID().toString();
        //调用加密方法进行加密
        String md5Pwd = getMd5Password(pwd,salt);

        adminUser.setPassword(md5Pwd);
        adminUser.setSalt(salt);
        //自动生成date时间
        adminUser.setCreateTime(new Date());
        adminUser.setId(IdWorker.getIdStr());
        adminUserMapper.insert(adminUser);
        return adminUser;
    }

    //更新AdminUser
    public AdminUser updateAdmin(EditAdminUserDto dto){
        AdminUser target = adminUserMapper.selectById(dto.getId());
        if (target == null){
            throw new WebException("不存在该id的公司");
        }
        target.setUsername(dto.getUsername());
        target.setPassword(null);
        target.setSalt(null);
        adminUserMapper.updateById(target);
        return target;
    }

    //删除AdminUser
    public void deleteAdmin(String id){
        AdminUser target = adminUserMapper.selectById(id);
        if(target == null){
            throw new WebException(id+"is null");
        }else {
            adminUserMapper.deleteById(id);
        }

    }

    //用于加密密码用的
    private String getMd5Password(String pwd,String salt){
        String str = salt+pwd+salt;

        for (int i=0;i<100;i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }

    //登录验证
    public String Login(LoginDto dto){

        //使用条件构造器来选择username相同的用户
        QueryWrapper<AdminUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",dto.getUsername());

        //
        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);

        if (adminUser==null){
            throw new WebException("密码或用户名错误");
        }

        String password = adminUser.getPassword();
        String salt = adminUser.getSalt();
        String md5Password = getMd5Password(dto.getPassword(), salt);


        if(!password.equals(md5Password)){
            throw new WebException("密码或用户名错误");
        }
        //返回一个token
        return jwtConfig.createToken(adminUser.getId());

    }
}
