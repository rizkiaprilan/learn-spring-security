package com.example.learnspringsecurity.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 07/04/2022 - 09:54:48
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "hibernate-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private String id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

}
