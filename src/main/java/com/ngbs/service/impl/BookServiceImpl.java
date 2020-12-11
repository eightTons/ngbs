package com.ngbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.dao.BookMapper;
import com.ngbs.pojo.Book;
import com.ngbs.pojo.LostAndFount;
import com.ngbs.service.IBookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iBookService")
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ServerResponse add(Integer userId, Book book) {
        if (book.getDescription() == null || "".equals(book.getDescription())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        book.setUserId(userId);

        int rowCount = bookMapper.insertSelective(book);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("增加图书信息成功");
        }

        return ServerResponse.createByErrorMessage("增加图书信息失败");
    }

    @Override
    public ServerResponse delete(Integer userId, Integer id) {
        if (id < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Book book = bookMapper.selectByPrimaryKey(id);
        if (book == null) {
            return ServerResponse.createByErrorMessage("该图书信息不存在");
        }
        if (!userId.equals(book.getUserId())) {
            return ServerResponse.createByErrorMessage("用户无权限删除");
        }

        int rowCount = bookMapper.deleteByPrimaryKey(id);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("删除图书信息成功");
        }

        return ServerResponse.createByErrorMessage("删除图书信息失败");
    }

    @Override
    public ServerResponse update(Integer userId, Book book) {
        if (book.getDescription() == null || "".equals(book.getDescription())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Book temp = bookMapper.selectByPrimaryKey(book.getId());
        if (!userId.equals(temp.getUserId())) {
            return ServerResponse.createByErrorMessage("用户无权限修改");
        }

        int rowCount = bookMapper.updateByPrimaryKeySelective(book);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("修改图书信息成功");
        }

        return ServerResponse.createByErrorMessage("修改图书信息失败");
    }

    @Override
    public ServerResponse<PageInfo> select(String keyword, int pageNum, int pageSize) {
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Book> lostAndFountList = bookMapper.selectByKeyword(StringUtils.isBlank(keyword)?null:keyword);
        PageInfo pageInfo = new PageInfo(lostAndFountList);

        return ServerResponse.createBySuccess(pageInfo);
    }
}
