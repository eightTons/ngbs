package com.ngbs.service;

import com.github.pagehelper.PageInfo;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;

import java.util.List;

public interface ICardService {

    ServerResponse add(Integer userId, Card card);

    ServerResponse<PageInfo> getCardByKeywordAndSchoolAndLocation(String keyword, List<String> schoolList, List<String> locationList, int pageNum, int pageSize);



}
