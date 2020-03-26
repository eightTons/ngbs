package com.ngbs.service.impl;

import com.ngbs.common.Const;
import com.ngbs.common.ServerResponse;
import com.ngbs.dao.UserMapper;
import com.ngbs.pojo.User;
import com.ngbs.service.IUserService;
import com.ngbs.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username, String password){
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("该用户未注册，请先注册");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<String> register(User user){
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        Integer userno = user.getUserno();
        if(userno != null){
            validResponse = this.checkValid(userno.toString(), Const.USERNO);
            if(!validResponse.isSuccess()){
                return validResponse;
            }
        }



        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");

    }

    public ServerResponse<String> checkValid(String str, String type){
        if(StringUtils.isNoneBlank(type) && StringUtils.isNoneBlank(str)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.USERNO.equals(type)){
                int resultCount = userMapper.checkUserno(Integer.parseInt(str));
                if(resultCount > 0){
                    return ServerResponse.createByErrorMessage("学号已存在");
                }
            }

        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return ServerResponse.createBySuccessMessage("校验成功");
    }

}
