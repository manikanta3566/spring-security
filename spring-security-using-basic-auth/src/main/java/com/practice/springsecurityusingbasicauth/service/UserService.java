package com.practice.springsecurityusingbasicauth.service;

import com.practice.springsecurityusingbasicauth.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User userDto);

    List<User> getAllUsers();

    User getUserById(String userId);

    User updateUser(User user, String userId,String roleId);

    void deleteUser(String id);

}
