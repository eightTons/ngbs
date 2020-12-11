package com.ngbs.controller;

import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Book;
import com.ngbs.pojo.User;
import com.ngbs.service.IBookService;
import com.ngbs.util.CookieUtil;
import com.ngbs.util.JsonUtil;
import com.ngbs.util.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/book/")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpServletRequest httpServletRequest, Book book) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        return iBookService.add(user.getId(), book);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete(HttpServletRequest httpServletRequest, Integer id) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        return iBookService.delete(user.getId(), id);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update(HttpServletRequest httpServletRequest, Book book) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        return iBookService.update(user.getId(), book);
    }

    @RequestMapping(value = "select.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse select(HttpServletRequest httpServletRequest,
                                 @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        return iBookService.select(keyword, pageNum, pageSize);
    }
}
