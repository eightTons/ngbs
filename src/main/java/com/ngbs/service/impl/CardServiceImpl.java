package com.ngbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.dao.CardMapper;
import com.ngbs.pojo.Card;
import com.ngbs.service.ICardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("iCardService")
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardMapper cardMapper;



    public ServerResponse add(Integer userId, Card card){
        if(card.getCardContact() == null || "".equals(card.getCardContact())){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        card.setUserId(userId);
        int rowCount = cardMapper.insertSelective(card);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("发布校卡成功");
        }

        return ServerResponse.createByErrorMessage("发布校卡失败");
    }

    @Override
    public ServerResponse delete(Integer userId, Integer id) {
        return null;
    }

    @Override
    public ServerResponse update(Integer userId, Card card) {
        return null;
    }

    public ServerResponse<PageInfo> getCardByKeywordAndSchoolAndLocation(String keyword, List<String> schoolList, List<String> locationList, int pageNum, int pageSize){
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Card> cardList = cardMapper.selectByKeywordAndSchoolAndLocation(StringUtils.isBlank(keyword)?null:keyword, schoolList.size()==0?null:schoolList, locationList.size()==0?null:locationList);

        PageInfo pageInfo = new PageInfo(cardList);

        return ServerResponse.createBySuccess(pageInfo);
    }

}
