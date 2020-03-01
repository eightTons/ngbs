package com.ngbs.service;

import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.User;

public interface IUserService {

    ServerResponse<User> login(Integer userno, String password);
}
