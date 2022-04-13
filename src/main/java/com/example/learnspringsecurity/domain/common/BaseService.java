package com.example.learnspringsecurity.domain.common;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 13/04/2022 - 11:08:25
 */
public interface BaseService<REQUEST extends BaseRequest, RESPONSE extends BaseResponse> {
    RESPONSE execute(REQUEST request);
}
