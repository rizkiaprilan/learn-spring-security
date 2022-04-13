package com.example.learnspringsecurity;

import com.example.learnspringsecurity.domain.model.Permission;
import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.service.PermissionService;
import com.example.learnspringsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class LearnSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, PermissionService permissionService) {
        return args -> {
//            Role roleUser = userService.saveRole(new Role(null, "ROLE_USER"));
//            Role roleManager = userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            Role roleAdmin = userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            Role roleSuperAdmin = userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(null, "Rizki Aprilan", "rizkiaprilan", "rizkiaprilan@gmail.com", "1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Nur Annisa", "nurannisa", "nurannisa@gmail.com", "1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Uzumaki Naruto", "narutoshippuden", "narutoshippuden@gmail.com", "12345", new ArrayList<>()));
//            userService.saveUser(new User(null, "Sasuke Uchiha", "sasukeshippuden", "sasukeshippuden@gmail.com", "123456", new ArrayList<>()));
//            userService.saveUser(new User(null, "Haruno Sakura", "sakurashippuden", "sakurashippuden@gmail.com", "1234567", new ArrayList<>()));
//
//            userService.addRoleToUser("nurannisa", "ROLE_USER");
//
//            userService.addRoleToUser("rizkiaprilan", "ROLE_USER");
//            userService.addRoleToUser("rizkiaprilan", "ROLE_MANAGER");
//
//            userService.addRoleToUser("narutoshippuden", "ROLE_MANAGER");
//
//            userService.addRoleToUser("sasukeshippuden", "ROLE_ADMIN");
//
//            userService.addRoleToUser("sakurashippuden", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("sakurashippuden", "ROLE_USER");
//            userService.addRoleToUser("sakurashippuden", "ROLE_ADMIN");
//
//            permissionService.savePermission(new Permission(null, "/api/admin-only", HttpMethod.GET.name(), List.of(roleAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/user-only", HttpMethod.GET.name(), List.of(roleUser)));
//            permissionService.savePermission(new Permission(null, "/api/user/**", HttpMethod.GET.name(), List.of(roleManager, roleAdmin, roleSuperAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/users", HttpMethod.GET.name(), List.of(roleManager, roleAdmin, roleSuperAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/user/save", HttpMethod.POST.name(), List.of(roleManager, roleAdmin, roleSuperAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/role/save", HttpMethod.POST.name(), List.of(roleManager, roleAdmin, roleSuperAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/role/addtouser", HttpMethod.POST.name(), List.of(roleManager, roleAdmin, roleSuperAdmin)));
//            permissionService.savePermission(new Permission(null, "/api/token/refresh", HttpMethod.POST.name(), List.of(roleUser, roleManager, roleAdmin, roleSuperAdmin)));

            permissionService.getPermissions().forEach(permission -> {
                List<String> roles = permission.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
                log.info("Method: {}, Path: {}, Roles: {}", permission.getHttpMethod(),permission.getPath(), roles.toArray(String[]::new));
            });

        };
    }
}
