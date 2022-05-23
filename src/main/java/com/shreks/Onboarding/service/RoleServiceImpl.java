package com.shreks.Onboarding.service;

import com.shreks.Onboarding.data.entity.RoleEntity;
import com.shreks.Onboarding.data.model.Role;
import com.shreks.Onboarding.data.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
        return  roleEntity;
    }

    private RoleEntity mapRoleDTOToEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(role.getRoleName());
        return roleEntity;
    }

    private Role mapRoleDTO(RoleEntity roleEntity) {
        Role role = new Role();
        role.setRoleId(roleEntity.getRoleId());
        role.setRoleName(roleEntity.getRoleName());
        return role;
    }
}
