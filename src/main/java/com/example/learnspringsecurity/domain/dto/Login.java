package com.example.learnspringsecurity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 11:30:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
