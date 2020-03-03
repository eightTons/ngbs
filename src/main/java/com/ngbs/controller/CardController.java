package com.ngbs.controller;

import com.ngbs.common.Const;
import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;
import com.ngbs.pojo.User;
import com.ngbs.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/card/")
public class CardController {

    @Autowired
    private ICardService iCardService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpSession session, Card card){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCardService.add(user.getId(), card);
    }

}
