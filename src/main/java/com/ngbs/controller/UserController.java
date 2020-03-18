package com.ngbs.controller;

import com.ngbs.common.Const;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.User;
import com.ngbs.service.IUserService;
import com.ngbs.util.CookieUtil;
import com.ngbs.util.JsonUtil;
import com.ngbs.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("login")
    private String login() {
        return "/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<User> login(@RequestParam(value = "userno") Integer userno,
                        @RequestParam(value = "password") String password,
                        HttpSession session,
                        HttpServletResponse httpServletResponse) {

        ServerResponse<User> response = iUserService.login(userno, password);
        if (response.isSuccess()) {
//            session.setAttribute(Const.CURRENT_USER, response.getData());

            CookieUtil.writeLoginToken(httpServletResponse, session.getId());
            RedisPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }

//        return "redirect:/card/publishcard";
//        return "/publishcard";

        return response;
    }

}
