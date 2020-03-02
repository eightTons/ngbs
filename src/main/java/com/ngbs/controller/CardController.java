package com.ngbs.controller;

import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;
import com.ngbs.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/card/")
public class CardController {

    @Autowired
    private ICardService iCardService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Card card){
        return ServerResponse.createBySuccess();
    }

}
