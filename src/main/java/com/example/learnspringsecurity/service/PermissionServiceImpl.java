package com.example.learnspringsecurity.service;

import com.example.learnspringsecurity.config.exception.CustomForbiddenException;
import com.example.learnspringsecurity.domain.model.Permission;
import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.repository.PermissionRepository;
import com.example.learnspringsecurity.repository.RoleRepository;
import com.example.learnspringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 10:16:41
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }
}
