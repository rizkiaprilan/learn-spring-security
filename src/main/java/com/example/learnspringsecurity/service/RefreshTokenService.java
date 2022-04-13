package com.example.learnspringsecurity.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.learnspringsecurity.config.exception.CustomBadRequestException;
import com.example.learnspringsecurity.config.exception.CustomForbiddenException;
import com.example.learnspringsecurity.domain.common.BaseService;
import com.example.learnspringsecurity.domain.common.EmptyRequest;
import com.example.learnspringsecurity.domain.model.Role;
import com.example.learnspringsecurity.domain.model.User;
import com.example.learnspringsecurity.domain.response.TokensResponse;
import com.example.learnspringsecurity.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 13/04/2022 - 11:13:47
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService implements BaseService<EmptyRequest, TokensResponse> {
    private final UserService userService;

    @Override
    public TokensResponse execute(EmptyRequest request) {
        String authorizationHeader = request.getServletRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (ObjectUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith(Constants.BEARER)) {
            try {
                String refreshToken = authorizationHeader.substring(Constants.BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(Constants.JWT_SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                        .withIssuer(request.getServletRequest().getRequestURL().toString())
                        .withClaim(Constants.ROLES, user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                return new TokensResponse(accessToken, refreshToken);
            } catch (Exception exception) {
                log.error("Error : {}", exception.getMessage());
                throw new CustomForbiddenException(exception.getMessage());
            }
        } else {
            String error = "Authorization refresh token is required";
            log.error("Error : {}", error);
            throw new CustomBadRequestException(error);
        }
    }
}
