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
 * @created 07/04/2022 - 09:48:13
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }, indexes = {
        @Index(name = "USER_INDX_0", columnList = "username"),
        @Index(name = "USER_INDX_1", columnList = "email")
})
public class User {
    @Id
    @GeneratedValue(generator = "hibernate-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "user_id", nullable = false, updatable = false, unique = true)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

}
