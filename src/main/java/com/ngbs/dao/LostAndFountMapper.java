package com.ngbs.dao;

import com.ngbs.pojo.Card;
import com.ngbs.pojo.LostAndFount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LostAndFountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostAndFount record);

    int insertSelective(LostAndFount record);

    LostAndFount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostAndFount record);

    int updateByPrimaryKey(LostAndFount record);

    List<LostAndFount> selectByKeyword(@Param("keyword") String keyword);
}