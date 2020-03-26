package com.ngbs.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Card {
    private Integer id;

    private Integer cardNo;

    private String cardName;

    private String cardSchool;

    private String cardLocation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cardTime;

    private String cardContact;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    public Card(Integer id, Integer cardNo, String cardName, String cardSchool, String cardLocation, Date cardTime, String cardContact, Integer userId, Date createTime, Date updateTime) {
        this.id = id;
        this.cardNo = cardNo;
        this.cardName = cardName;
        this.cardSchool = cardSchool;
        this.cardLocation = cardLocation;
        this.cardTime = cardTime;
        this.cardContact = cardContact;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Card() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getCardSchool() {
        return cardSchool;
    }

    public void setCardSchool(String cardSchool) {
        this.cardSchool = cardSchool == null ? null : cardSchool.trim();
    }

    public String getCardLocation() {
        return cardLocation;
    }

    public void setCardLocation(String cardLocation) {
        this.cardLocation = cardLocation == null ? null : cardLocation.trim();
    }

    public Date getCardTime() {
        return cardTime;
    }

    public void setCardTime(Date cardTime) {
        this.cardTime = cardTime;
    }

    public String getCardContact() {
        return cardContact;
    }

    public void setCardContact(String cardContact) {
        this.cardContact = cardContact == null ? null : cardContact.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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