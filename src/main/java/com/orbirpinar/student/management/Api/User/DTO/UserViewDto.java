package com.orbirpinar.student.management.Api.User.DTO;

import com.orbirpinar.student.management.Api.Role.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserViewDto implements Serializable {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Boolean emailVerified;

    private Boolean enabled;

    private List<Role> roles;
}
