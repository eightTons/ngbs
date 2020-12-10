package com.ngbs.service;

import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.User;

// 用户服务接口
public interface IUserService {

    // 登录
    ServerResponse<User> login(String userno, String password);

    // 注册
    public ServerResponse<String> register(User user);
}
