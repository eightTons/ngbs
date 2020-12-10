package com.ngbs.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LostAndFount {
    private Integer id;

    private Integer userId;

    private String foundLocation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date foundTime;

    private String description;

    private Date createTime;

    private Date updateTime;

    public LostAndFount(Integer id, Integer userId, String foundLocation, Date foundTime, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.foundLocation = foundLocation;
        this.foundTime = foundTime;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public LostAndFount() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation == null ? null : foundLocation.trim();
    }

    public Date getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}