package com.shreks.onboarding.data.model;

import java.util.Date;

public class User {
    private long userId;

    private String userName;

    private String lanId;

    private Date updateTime;

    private String updateBy;

    public User() {
    }

    public User(long userId, String userName, String lanId, Date updateTime, String updateBy) {
        this.userId = userId;
        this.userName = userName;
        this.lanId = lanId;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanId() {
        return lanId;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
