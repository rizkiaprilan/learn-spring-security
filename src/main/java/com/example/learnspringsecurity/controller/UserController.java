package com.example.learnspringsecurity.controller;

import com.example.learnspringsecurity.domain.common.EmptyRequest;
import com.example.learnspringsecurity.domain.common.RestResponse;
import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.domain.request.AddRoleToUserRequest;
import com.example.learnspringsecurity.domain.request.LoginRequest;
import com.example.learnspringsecurity.domain.response.TokensResponse;
import com.example.learnspringsecurity.service.RefreshTokenService;
import com.example.learnspringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user/{accountName}")
    public ResponseEntity<User> getUser(@PathVariable("accountName") String accountName) {
        return ResponseEntity.ok(userService.getUser(accountName));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/login").toUriString();
        RestResponse<LinkedHashMap<String, String>> response = restTemplate.postForObject(url, request, RestResponse.class);

        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, response.getData().get("accessToken"));
        cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(10));
        servletResponse.addCookie(cookie);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/sign-out")
    public ResponseEntity<RestResponse<Object>> signOut(HttpServletResponse servletResponse) {
        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, null);
        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
        return ResponseEntity.ok(new RestResponse<>(true, "logout", null));
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
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleToUserRequest form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<TokensResponse> refreshToken(HttpServletRequest servletRequest) {
        EmptyRequest request = new EmptyRequest();
        request.setServletRequest(servletRequest);
        TokensResponse tokensResponse = refreshTokenService.execute(new EmptyRequest());
        return ResponseEntity.ok(tokensResponse);
    }
}
