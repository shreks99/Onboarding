package com.shreks.onboarding.data.model;

import java.util.Date;

public class Role {
    private int roleId;

    private String roleName;

    private Date updateTime;

    private String updateBy;

    public Role() {
    }

    public Role(int roleId, String roleName, Date updateTime, String updateBy) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
