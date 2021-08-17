package com.thl.spring.dto;


import com.thl.spring.model.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
public class UserDto {
    private String username;
    private String password;
    private Set<Role> role;
}
