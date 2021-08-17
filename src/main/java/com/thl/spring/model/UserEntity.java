package com.thl.spring.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    public User toUser() {
        return new User(username, password, getAuthorities());
    }

    private List<GrantedAuthority> getAuthorities() {
        return role.stream().map(role
                -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toUnmodifiableList());
    }
}
