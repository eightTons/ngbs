package com.ngbs.service.impl;

import com.ngbs.common.ServerResponse;
import com.ngbs.dao.UserMapper;
import com.ngbs.pojo.User;
import com.ngbs.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(Integer userno, String password){
        int resultCount = userMapper.checkUserno(userno);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("该学号未注册，请先注册");
        }

        User user = userMapper.selectLogin(userno, password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

}
