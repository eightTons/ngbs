package com.ngbs.controller;

import com.ngbs.common.Const;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.User;
import com.ngbs.service.IUserService;
import com.ngbs.util.CookieUtil;
import com.ngbs.util.JsonUtil;
import com.ngbs.util.RedisPoolUtil;
import com.ngbs.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("login")
    private String login() {
        return "/login";
    }

    @RequestMapping("center")
    private String center() {
        return "/center";
    }

    @RequestMapping("register")
    private String register() {
        return "/register";
    }


    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpSession session,
                        HttpServletResponse httpServletResponse) {

        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
//            session.setAttribute(Const.CURRENT_USER, response.getData());
            CookieUtil.writeLoginToken(httpServletResponse, session.getId());
            RedisPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }

        return "redirect:/card/publishcard";

    }


    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "first_password") String firstPassword,
                           @RequestParam(value = "second_password") String secondPassword,
                           HttpSession httpSession, HttpServletResponse httpServletResponse) throws IOException {

        if(!firstPassword.equals(secondPassword)){
            return "redirect:/user/register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(firstPassword);
        ServerResponse<String> response = iUserService.register(user);

        if(response.isSuccess()){
            return "redirect:/user/login";
        }
        return "redirect:/user/register";
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String longout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
        RedisPoolUtil.del(loginToken);

        return "redirect:/user/login";
    }

}
