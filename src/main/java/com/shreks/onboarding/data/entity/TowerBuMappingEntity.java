package com.shreks.onboarding.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TOWER_BU_MAPPING")
public class TowerBuMappingEntity {
    @Id
    @Column(name = "BU_TOWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int buTowerId;

    @Column(name="BUSINESS_UNIT")
    private String businessUnit;

    @Column(name="TOWER")
    private String tower;

    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Column(name="UPDATE_BY")
    private String updateBy;

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
