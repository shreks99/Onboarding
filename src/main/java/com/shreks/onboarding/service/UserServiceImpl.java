package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.User;
import com.shreks.onboarding.data.repository.UserRepository;
import com.shreks.onboarding.util.MapperUtil;
import com.shreks.onboarding.util.exception.ApplicationErrorCodes;
import com.shreks.onboarding.util.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public static final String INVALID_USER_ID = "Invalid user Id:";
    final UserRepository userRepository;

    private final MapperUtil mapperUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        return userEntityList.stream().map(mapperUtil::mapUserDTO).collect(Collectors.toList());
    }

    @Override
    public void saveUser(User user) {
        try {
            UserEntity userEntity = mapperUtil.mapUserDTOToEntity(user);
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.INTERNAL_SERVER_ERROR, "Error in saving User");
        }
    }

    @Override
    public User getUserById(Long userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(INVALID_USER_ID + userId));
            return mapperUtil.mapUserDTO(userEntity);
        } catch (ApplicationException exception) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR,ApplicationErrorCodes.USER_NOT_FOUND,INVALID_USER_ID+userId);
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationErrorCodes.USER_NOT_FOUND, INVALID_USER_ID + userId);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        UserEntity updatedUserEntity = mapperUtil.mapUserDTOToExistingEntity(user);
        updatedUserEntity.setUserId(userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ApplicationException(
                        HttpStatus.NOT_FOUND,
                        ApplicationErrorCodes.USER_NOT_FOUND,
                        INVALID_USER_ID + user.getUserId()))
                .getUserId());
        userRepository.save(updatedUserEntity);
    }
}
