package com.shreks.onboarding.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PARTNER_DETAILS")
public class PartnerDetailsEntity {

    @Id
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "JOINING_DATE")
    private Date joiningDate;

    @Column(name = "DROPOUT_REASON")
    private String dropoutReason;

    @Column(name = "PERSONAL_EMAIL")
    private String personalEmail;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REQUISITION_NUMBER")
    private Integer requisitionNumber;

    @Column(name = "POSITION_NUMBER")
    private Integer positionNumber;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "LOCATION")
    private String location;

    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Column(name="UPDATE_BY")
    private String updateBy;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(Integer requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public Integer getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(Integer positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDropoutReason() {
        return dropoutReason;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDropoutReason(String dropoutReason) {
        this.dropoutReason = dropoutReason;
    }
}
