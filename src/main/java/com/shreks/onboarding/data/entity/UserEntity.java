package com.shreks.onboarding.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "LAN_ID")
    private String lanId;

    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Column(name="UPDATE_BY")
    private String updateBy;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLanId() {
        return lanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
