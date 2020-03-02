package com.ngbs.service.impl;

import com.ngbs.common.ServerResponse;
import com.ngbs.dao.CardMapper;
import com.ngbs.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Service("iCardService")
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardMapper cardMapper;



//    public ServerResponse add()

}
