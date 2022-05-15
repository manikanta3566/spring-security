package com.practice.SpringsecurityusingJWT.services;

import com.practice.SpringsecurityusingJWT.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    User updateUser(User user, String userId,String roleId);

    void deleteUser(String id);

}
