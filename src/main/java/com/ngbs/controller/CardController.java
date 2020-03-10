package com.ngbs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ngbs.common.Const;
import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;
import com.ngbs.pojo.User;
import com.ngbs.service.ICardService;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/card/")
public class CardController {

    @Autowired
    private ICardService iCardService;

    @RequestMapping("publishcard")
    private String publishCard() {
        return "/publishcard";
    }

    @RequestMapping("findcard")
    private String findCard() {
        return "/findcard";
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpSession session, Card card) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCardService.add(user.getId(), card);
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "search", defaultValue = "") String search,
                                         @RequestParam(value = "searchSchool")String[] searchSchool,
                                         @RequestParam(value = "searchLocation")String[] searchLocation,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        List<String> schoolList = new ArrayList<>();
        List<String> locationList = new ArrayList<>();
        if(searchSchool.length>0){
            for(String item : searchSchool){
                schoolList.add(item);
            }
        }
        if(searchLocation.length>0){
            for(String item : searchLocation){
                locationList.add(item);
            }
        }

//        PageInfo pageInfo = new PageInfo();
//        return ServerResponse.createBySuccess(pageInfo);



        return iCardService.getCardByKeywordAndSchoolAndLocation(search, schoolList, locationList, pageNum, pageSize);
    }

}
