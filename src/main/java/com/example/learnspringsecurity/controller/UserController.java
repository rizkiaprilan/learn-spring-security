package com.example.learnspringsecurity.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.learnspringsecurity.config.exception.CustomForbiddenException;
import com.example.learnspringsecurity.domain.RestResponse;
import com.example.learnspringsecurity.domain.dto.RoleToUserForm;
import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.service.UserService;
import com.example.learnspringsecurity.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 10:36:21
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/admin-only")
    public ResponseEntity<String> accessByAdmin() {
        return ResponseEntity.ok("This api only access by admin");
    }

    @GetMapping("/user-only")
    public ResponseEntity<String> accessByUser() {
        return ResponseEntity.ok("This api only access by user");
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role, HttpServletRequest servletRequest) {
        URI uri = URI.create(servletRequest.getRequestURI());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        String authorizationHeader = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (ObjectUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(Constants.JWT_SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                        .withIssuer(servletRequest.getRequestURL().toString())
                        .withClaim(Constants.ROLES, user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put(Constants.ACCESS_TOKEN, accessToken);
                tokens.put(Constants.REFRESH_TOKEN, refreshToken);
                servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(servletResponse.getOutputStream(), tokens);
            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                servletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                RestResponse<Object> restResponse = new RestResponse<>(exception.getMessage(),null);
                new ObjectMapper().writeValue(servletResponse.getOutputStream(), restResponse);
            }
        } else {
            throw new CustomForbiddenException("Refresh token is missing");
        }
    }
}
