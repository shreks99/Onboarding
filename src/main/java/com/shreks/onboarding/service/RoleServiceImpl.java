package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.RoleEntity;
import com.shreks.onboarding.data.model.Role;
import com.shreks.onboarding.data.repository.RoleRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    final RoleRepository roleRepository;

    private final DateTimeUtil dateTimeUtil;

    public RoleServiceImpl(RoleRepository roleRepository, DateTimeUtil dateTimeUtil) {
        this.roleRepository = roleRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    @Transactional
    public List<Role> getAllRole() {
        List<RoleEntity> roleEntityList = (List<RoleEntity>) roleRepository.findAll();
        return roleEntityList.stream().map(this::mapRoleDTO).collect(Collectors.toList());
    }

    @Override
    public void saveRole(Role role) {
        RoleEntity roleEntity = mapRoleDTOToEntity(role);
        roleRepository.save(roleEntity);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        //Add try/catch block for exception handling
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + roleId));
        return mapRoleDTO(roleEntity);
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        RoleEntity updatedRoleEntity = mapRoleDTOToExistingEntity(role);
        roleRepository.save(updatedRoleEntity);
    }

    private RoleEntity mapRoleDTOToExistingEntity(Role role) {
        RoleEntity roleEntity = roleRepository.findById(role.getRoleId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + role.getRoleId()));
        //Add modified time
        roleEntity.setRoleName(role.getRoleName());
        roleEntity.setUpdateBy(role.getUpdateBy());
        roleEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return  roleEntity;
    }

    private RoleEntity mapRoleDTOToEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(role.getRoleName());
        roleEntity.setUpdateBy(role.getUpdateBy());
        roleEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return roleEntity;
    }

    private Role mapRoleDTO(RoleEntity roleEntity) {
        Role role = new Role();
        role.setRoleId(roleEntity.getRoleId());
        role.setRoleName(roleEntity.getRoleName());
        role.setUpdateBy(roleEntity.getUpdateBy());
        role.setUpdateTime(roleEntity.getUpdateTime());
        return role;
    }
}
