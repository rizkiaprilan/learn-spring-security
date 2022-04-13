package com.example.learnspringsecurity.repository;

import com.example.learnspringsecurity.domain.model.Permission;
import com.example.learnspringsecurity.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 10:04:49
 */

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
