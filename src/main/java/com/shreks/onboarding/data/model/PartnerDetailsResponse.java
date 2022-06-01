package com.shreks.onboarding.data.model;

import java.util.Date;

public class PartnerDetailsResponse {

    private long userId;
    private String partnerName;
    private String lanId;
    private String hiringManager;
    private Date joiningDate;
    private int roleId;
    private String buddy;
    private String status;
    private String address;
    private String phone;
    private String email;
    private String tower;
    private String businessUnit;
    private String dropoutReason;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getLanId() {
        return lanId;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    public String getHiringManager() {
        return hiringManager;
    }

    public void setHiringManager(String hiringManager) {
        this.hiringManager = hiringManager;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getBuddy() {
        return buddy;
    }

    public void setBuddy(String buddy) {
        this.buddy = buddy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getDropoutReason() {
        return dropoutReason;
    }

    public void setDropoutReason(String dropoutReason) {
        this.dropoutReason = dropoutReason;
    }

    public PartnerDetailsResponse() {
    }

    public PartnerDetailsResponse(long userId, String partnerName, String lanId, String hiringManager, Date joiningDate, int roleId, String buddy, String status, String address, String phone, String email, String tower, String businessUnit, String dropoutReason) {
        this.userId = userId;
        this.partnerName = partnerName;
        this.lanId = lanId;
        this.hiringManager = hiringManager;
        this.joiningDate = joiningDate;
        this.roleId = roleId;
        this.buddy = buddy;
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.tower = tower;
        this.businessUnit = businessUnit;
        this.dropoutReason = dropoutReason;
    }
}
