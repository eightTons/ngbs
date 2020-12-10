package com.ngbs.service;

import com.github.pagehelper.PageInfo;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;

import java.util.List;

// 校卡服务接口
public interface ICardService {

    // 增加校卡
    ServerResponse add(Integer userId, Card card);

    // 根据条件查找校卡
    ServerResponse<PageInfo> getCardByKeywordAndSchoolAndLocation(String keyword, List<String> schoolList, List<String> locationList, int pageNum, int pageSize);



}
