package com.shreks.onboarding.util;

import com.shreks.onboarding.data.entity.RoleEntity;
import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.Role;
import com.shreks.onboarding.data.model.User;
import com.shreks.onboarding.data.repository.UserRepository;
import com.shreks.onboarding.util.exception.ApplicationErrorCodes;
import com.shreks.onboarding.util.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    final UserRepository userRepository;

    private final DateTimeUtil dateTimeUtil;

    @Autowired
    public MapperUtil(UserRepository userRepository, DateTimeUtil dateTimeUtil) {
        this.userRepository = userRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    private static final String USER_NOT_FOUND = "Failed to get User by Id";

    /**
     * This function maps Role model to RoleEntity
     * @param role Role model class
     * @return RoleEntity
     */
    public RoleEntity mapRoleDTOToEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(role.getRoleName());
        roleEntity.setUpdateBy(role.getUpdateBy());
        roleEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return roleEntity;
    }

    /**
     * This function maps RoleEntity to Role model class
     * @param roleEntity RoleEntity
     * @return Role model class
     */
    public Role mapRoleDTO(RoleEntity roleEntity) {
        Role role = new Role();
        role.setRoleId(roleEntity.getRoleId());
        role.setRoleName(roleEntity.getRoleName());
        role.setUpdateBy(roleEntity.getUpdateBy());
        role.setUpdateTime(roleEntity.getUpdateTime());
        return role;
    }

    /**
     * This function maps userEntity with User model
     * @param userEntity UserEntity
     * @return User
     */
    public User mapUserDTO(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setLanId(userEntity.getLanId());
        user.setUpdateBy(userEntity.getUpdateBy());
        user.setUpdateTime(userEntity.getUpdateTime());
        if(userEntity.getRoleEntity() != null)
            user.setRole(mapRoleDTO(userEntity.getRoleEntity()));
        return user;
    }

    /**
     * This function maps User model to UserEntity
     * @param user User model class
     * @return UserEntity
     */
    public UserEntity mapUserDTOToEntity(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId()).orElse(new UserEntity());
        userEntity.setUserName(user.getUserName());
        userEntity.setLanId(user.getLanId());
        userEntity.setUpdateBy(user.getUpdateBy());
        userEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
//      userEntity.setRoleEntity(roleRepository.findById(user.getRole().getRoleId()).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + user.getRole().getRoleId())));
        return userEntity;
    }

    /**
     * This function checks existing entity
     * @param user User model class
     * @return UserEntity
     */
    public UserEntity mapUserDTOToExistingEntity(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId()).orElseThrow(() -> new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.USER_NOT_FOUND, USER_NOT_FOUND));
        userEntity = mapUserDTOToEntity(user);
        userEntity.setUserId(user.getUserId());
        return userEntity;
    }
}
