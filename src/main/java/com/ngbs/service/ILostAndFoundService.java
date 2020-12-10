package com.ngbs.service;

import com.github.pagehelper.PageInfo;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.LostAndFount;

public interface ILostAndFoundService {
    // 增加失物招领信息
    ServerResponse add(Integer userId, LostAndFount lostAndFount);

    // 删除失物招领信息
    ServerResponse delete(Integer userId, Integer id);

    // 修改失物招领信息
    ServerResponse update(Integer userId, LostAndFount lostAndFount);

    // 查找失物招领信息
    ServerResponse<PageInfo> select(String keyword, int pageNum, int pageSize);
}
