package com.shreks.onboarding.data.model;

import javax.persistence.Column;
import java.util.Date;

public class TowerBuMapping {
    private int buTowerId;

    private String businessUnit;

    private String tower;

    private Date updateTime;

    private String updateBy;

    public TowerBuMapping() {
    }

    public TowerBuMapping(int buTowerId, String businessUnit, String tower, Date updateTime, String updateBy) {
        this.buTowerId = buTowerId;
        this.businessUnit = businessUnit;
        this.tower = tower;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public int getBuTowerId() {
        return buTowerId;
    }

    public void setBuTowerId(int buTowerId) {
        this.buTowerId = buTowerId;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
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
