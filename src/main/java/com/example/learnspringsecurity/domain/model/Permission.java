package com.example.learnspringsecurity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
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
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(generator = "hibernate-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "permission_id", nullable = false, updatable = false, unique = true)
    private String id;
    @Column(name = "path", nullable = false, unique = true)
    private String path;
    @Column(name = "http_method", nullable = false)
    private String httpMethod;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "permissions_roles",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

}
