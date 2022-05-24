package com.shreks.onboarding.service;

import com.shreks.onboarding.data.entity.UserEntity;
import com.shreks.onboarding.data.model.User;
import com.shreks.onboarding.data.repository.UserRepository;
import com.shreks.onboarding.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    private final DateTimeUtil dateTimeUtil;

    public UserServiceImpl(UserRepository userRepository, DateTimeUtil dateTimeUtil) {
        this.userRepository = userRepository;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        return userEntityList.stream().map(this::mapUserDTO).collect(Collectors.toList());
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = mapUserDTOToEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserById(Long userId) {
        //Add try/catch block for exception handling
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        return mapUserDTO(userEntity);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        UserEntity updatedUserEntity = mapUserDTOToExistingEntity(user);
        userRepository.save(updatedUserEntity);
    }

    private UserEntity mapUserDTOToExistingEntity(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + user.getUserId()));
        //Add modified time
        userEntity.setUserName(user.getUserName());
        userEntity.setLanId(user.getLanId());
        userEntity.setUpdateBy(user.getUpdateBy());
        userEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return  userEntity;
    }

    private UserEntity mapUserDTOToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setLanId(user.getLanId());
        userEntity.setUpdateBy(user.getUpdateBy());
        userEntity.setUpdateTime(dateTimeUtil.getCurrentUTCTimestamp());
        return userEntity;
    }

    private User mapUserDTO(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setLanId(userEntity.getLanId());
        user.setUpdateBy(userEntity.getUpdateBy());
        user.setUpdateTime(userEntity.getUpdateTime());
        return user;
    }
}
