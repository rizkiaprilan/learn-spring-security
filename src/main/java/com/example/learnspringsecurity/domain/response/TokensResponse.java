package com.example.learnspringsecurity.domain.response;

import com.example.learnspringsecurity.domain.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 13/04/2022 - 11:20:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensResponse extends BaseResponse {
    String accessToken;
    String refreshToken;
}
