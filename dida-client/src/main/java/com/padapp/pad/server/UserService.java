package com.padapp.pad.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.padapp.pad.entity.User;
import com.padapp.pad.exception.WebException;
import com.padapp.pad.mapper.UserMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> getlst(){

        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        ConcurrentHashMap<String,List<User>> map =new ConcurrentHashMap<>();
        List<User> userList=userMapper.selectList(queryWrapper);

        return userList;

    }

    public User addUser(User user){
        //自动生成id

        user.setId(IdWorker.getIdStr());
        userMapper.insert(user);
        return user;
    }

    public User update(User user){
        if (userMapper.selectById(user.getId()) == null){
            throw new WebException("不存在该id的公司");
        }
        userMapper.updateById(user);
        return user;
    }

    public void delete(String id){
        User target = userMapper.selectById(id);
        if (target == null){
            throw new WebException(id+"is null");
        }
        userMapper.deleteById(id);
    }
}
