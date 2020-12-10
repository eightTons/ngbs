package com.ngbs.controller;


import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;
import com.ngbs.pojo.LostAndFount;
import com.ngbs.pojo.User;
import com.ngbs.service.ILostAndFoundService;
import com.ngbs.service.impl.LostAndFoundServiceImpl;
import com.ngbs.util.CookieUtil;
import com.ngbs.util.JsonUtil;
import com.ngbs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/lost/")
public class LostAndFoundController {


    @Autowired
    private ILostAndFoundService iLostAndFoundService;


    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpServletRequest httpServletRequest, LostAndFount lostAndFount) {

        return iLostAndFoundService.add(1, lostAndFount);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete(HttpServletRequest httpServletRequest, Integer id) {

        return iLostAndFoundService.delete(1, id);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update(HttpServletRequest httpServletRequest, LostAndFount lostAndFount) {

        return iLostAndFoundService.update(1, lostAndFount);
    }

    @RequestMapping(value = "select.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse select(HttpServletRequest httpServletRequest, String keyword,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        return iLostAndFoundService.select(keyword, pageNum, pageSize);
    }

}
