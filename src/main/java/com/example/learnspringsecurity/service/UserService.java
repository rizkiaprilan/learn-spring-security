package com.example.learnspringsecurity.service;

import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;

import java.util.List;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 10:14:05
 */
public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    User getUser(String username);

    List<User> getUsers();
}
