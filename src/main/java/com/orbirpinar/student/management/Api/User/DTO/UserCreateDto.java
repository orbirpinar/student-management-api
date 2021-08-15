package com.orbirpinar.student.management.Api.User.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserCreateDto implements Serializable {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Boolean emailVerified;

    private Boolean enabled;
}
