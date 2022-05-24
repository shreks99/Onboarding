package com.shreks.onboarding.rest;

import com.shreks.onboarding.data.model.Role;
import com.shreks.onboarding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @PostMapping
    public void saveRole(@RequestBody Role role) {
        roleService.saveRole(role);
    }

    @GetMapping(path="/{roleId}")
    public Role getRoleById(@PathVariable("roleId") Integer roleId) {
        return roleService.getRoleById(roleId);
    }

    @DeleteMapping(path="/{roleId}")
    public void deleteRoleById(@PathVariable("roleId") Integer roleId) {
        roleService.deleteRoleById(roleId);
    }

    @PutMapping
    public void updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
    }
}
