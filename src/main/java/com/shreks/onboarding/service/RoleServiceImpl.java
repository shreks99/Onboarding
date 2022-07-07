package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.RoleEntity;
import com.shreks.onboarding.data.model.Role;
import com.shreks.onboarding.data.repository.RoleRepository;
import com.shreks.onboarding.util.MapperUtil;
import com.shreks.onboarding.util.exception.ApplicationErrorCodes;
import com.shreks.onboarding.util.exception.ApplicationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    final RoleRepository roleRepository;

    private final MapperUtil mapperUtil;

    public static final String INVALID_ROLE_ID = "Invalid role Id:";

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Transactional
    public List<Role> getAllRole() {
        List<RoleEntity> roleEntityList = (List<RoleEntity>) roleRepository.findAll();
        return roleEntityList.stream().map(mapperUtil::mapRoleDTO).collect(Collectors.toList());
    }

    @Override
    public void saveRole(Role role) {
        try {
            RoleEntity roleEntity = mapperUtil.mapRoleDTOToEntity(role);
            roleRepository.save(roleEntity);
        } catch (Exception e) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.INTERNAL_SERVER_ERROR, "Error in saving Role");
        }

    }

    @Override
    public Role getRoleById(Integer roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.ROLE_NOT_FOUND, INVALID_ROLE_ID + roleId));
        return mapperUtil.mapRoleDTO(roleEntity);
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        try{
            roleRepository.deleteById(roleId);
        } catch(EmptyResultDataAccessException e) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.ROLE_NOT_FOUND, INVALID_ROLE_ID + roleId);
        }
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        RoleEntity updatedRoleEntity = mapperUtil.mapRoleDTOToEntity(role);
        Optional<RoleEntity> roleEnitity = roleRepository.findById(role.getRoleId());
        if(roleEnitity.isPresent()){
            //update logic
            updatedRoleEntity.setRoleId(role.getRoleId());
        }
            roleRepository.save(updatedRoleEntity);






        updatedRoleEntity.setRoleId(roleRepository.findById(role.getRoleId())
                .orElseThrow(()->new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.ROLE_NOT_FOUND, INVALID_ROLE_ID + role.getRoleId()))
                .getRoleId());
        roleRepository.save(updatedRoleEntity);
    }
}
