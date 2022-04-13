package com.example.learnspringsecurity.domain.request;

import com.example.learnspringsecurity.domain.common.BaseRequest;
import lombok.Data;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 11:30:01
 */
@Data
public class AddRoleToUserRequest extends BaseRequest {
    private String username;
    private String rolename;
}
