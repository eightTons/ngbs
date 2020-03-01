package com.ngbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class test {

    @RequestMapping("login")
    private String login(){
        return "/login";
    }


    @RequestMapping("publishcard")
    private String publishCard(){
        return "/publishcard";
    }

    @RequestMapping("findcard")
    private String findCard(){
        return "/findcard";
    }

}
