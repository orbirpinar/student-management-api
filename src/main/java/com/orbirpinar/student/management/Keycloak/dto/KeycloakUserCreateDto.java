package com.orbirpinar.student.management.Keycloak.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KeycloakUserCreateDto {
    private String username;
    private Boolean enabled = true;
    private Boolean emailVerified = true;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> requiredActions = new ArrayList<>();
}
