package com.shreks.Onboarding.data.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ROLE")
public class RoleEntity {
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    @Column(name="ROLE_NAME")
    private String roleName;

    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Column(name="UPDATE_BY")
    private String updateBy;


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
