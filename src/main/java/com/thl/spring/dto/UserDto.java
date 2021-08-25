package com.thl.spring.dto;


import com.thl.spring.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDto {
    private String username;
    private String password;
    private Set<Role> role;
}
