package com.shreks.onboarding.service;

import com.shreks.onboarding.data.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

    void updateUser(User user);
}
