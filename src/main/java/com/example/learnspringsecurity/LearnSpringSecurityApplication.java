package com.example.learnspringsecurity;

import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

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
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Rizki Aprilan", "rizkiaprilan","rizkiaprilan@gmail.com", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Uzumaki Naruto", "narutoshippuden","narutoshippuden@gmail.com", "12345", new ArrayList<>()));
            userService.saveUser(new User(null, "Sasuke Uchiha", "sasukeshippuden","sasukeshippuden@gmail.com", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Haruno Sakura", "sakurashippuden","sakurashippuden@gmail.com", "1234567", new ArrayList<>()));

            userService.addRoleToUser("rizkiaprilan", "ROLE_USER");
            userService.addRoleToUser("rizkiaprilan", "ROLE_MANAGER");

            userService.addRoleToUser("narutoshippuden", "ROLE_MANAGER");

            userService.addRoleToUser("sasukeshippuden", "ROLE_ADMIN");

            userService.addRoleToUser("sakurashippuden", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("sakurashippuden", "ROLE_USER");
            userService.addRoleToUser("sakurashippuden", "ROLE_ADMIN");
        };
    }
}
