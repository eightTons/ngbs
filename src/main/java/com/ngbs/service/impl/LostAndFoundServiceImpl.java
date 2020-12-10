package com.ngbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ngbs.common.ResponseCode;
import com.ngbs.common.ServerResponse;
import com.ngbs.dao.LostAndFountMapper;
import com.ngbs.pojo.LostAndFount;
import com.ngbs.service.ILostAndFoundService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iLostAndFoundService")
public class LostAndFoundServiceImpl implements ILostAndFoundService {

    @Autowired
    private LostAndFountMapper lostAndFountMapper;

    @Override
    public ServerResponse add(Integer userId, LostAndFount lostAndFount) {
        if (lostAndFount.getDescription() == null || "".equals(lostAndFount.getDescription())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        lostAndFount.setUserId(userId);

        int rowCount = lostAndFountMapper.insertSelective(lostAndFount);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("增加失物招领信息成功");
        }

        return ServerResponse.createByErrorMessage("增加失物招领信息失败");
    }

    @Override
    public ServerResponse delete(Integer userId, Integer id) {
        if (id < 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        LostAndFount lostAndFount = lostAndFountMapper.selectByPrimaryKey(id);
        if (!userId.equals(lostAndFount.getUserId())) {
            return ServerResponse.createByErrorMessage("用户无权限删除");
        }

        int rowCount = lostAndFountMapper.deleteByPrimaryKey(id);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("删除失物招领信息成功");
        }

        return ServerResponse.createByErrorMessage("删除失物招领信息失败");
    }

    @Override
    public ServerResponse update(Integer userId, LostAndFount lostAndFount) {
        if (lostAndFount.getDescription() == null || "".equals(lostAndFount.getDescription())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        LostAndFount temp = lostAndFountMapper.selectByPrimaryKey(lostAndFount.getId());
        if (!userId.equals(temp.getUserId())) {
            return ServerResponse.createByErrorMessage("用户无权限修改");
        }

        int rowCount = lostAndFountMapper.updateByPrimaryKeySelective(lostAndFount);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("修改失物招领信息成功");
        }

        return ServerResponse.createByErrorMessage("修改失物招领信息失败");
    }

    @Override
    public ServerResponse<PageInfo> select(String keyword, int pageNum, int pageSize) {
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<LostAndFount> lostAndFountList = lostAndFountMapper.selectByKeyword(StringUtils.isBlank(keyword)?null:keyword);
        PageInfo pageInfo = new PageInfo(lostAndFountList);

        return ServerResponse.createBySuccess(pageInfo);
    }
}
