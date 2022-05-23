package com.shreks.Onboarding.data.entity;


import javax.persistence.*;

@Entity
@Table(name="ROLE")
public class RoleEntity {
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    @Column(name="ROLE_NAME")
    private String roleName;

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
}
