package com.ngbs.service;

import com.github.pagehelper.PageInfo;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Book;
import com.ngbs.pojo.LostAndFount;

public interface IBookService {
    // 增加图书信息
    ServerResponse add(Integer userId, Book book);

    // 删除图书信息
    ServerResponse delete(Integer userId, Integer id);

    // 修改图书信息
    ServerResponse update(Integer userId, Book book);

    // 查找图书信息
    ServerResponse<PageInfo> select(String keyword, int pageNum, int pageSize);
}
