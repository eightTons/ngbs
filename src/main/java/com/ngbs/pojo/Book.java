package com.ngbs.pojo;

import java.util.Date;

public class Book {
    private Integer id;

    private Integer userId;

    private String bookName;

    private String bookAuthor;

    private String description;

    private Date createTime;

    private Date updateTime;

    public Book(Integer id, Integer userId, String bookName, String bookAuthor, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Book() {
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
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