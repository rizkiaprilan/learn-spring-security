package com.example.learnspringsecurity.service;

import com.example.learnspringsecurity.domain.model.Permission;

import java.util.List;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 10:14:05
 */
public interface PermissionService {
    Permission savePermission(Permission permission);
    List<Permission> getPermissions();
}
