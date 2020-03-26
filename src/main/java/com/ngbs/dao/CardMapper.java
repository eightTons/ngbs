package com.ngbs.dao;

import com.ngbs.pojo.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    List<Card> selectByKeywordAndSchoolAndLocation(@Param("keyword") String keyword, @Param("schoolList") List<String> schoolList, @Param("locationList") List<String> locationList);

}