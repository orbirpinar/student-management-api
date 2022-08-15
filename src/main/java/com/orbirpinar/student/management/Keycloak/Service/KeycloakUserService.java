package com.orbirpinar.student.management.Keycloak.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbirpinar.student.management.Api.Role.DTO.RoleViewDto;
import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Keycloak.Client.KeycloakClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class KeycloakUserService {

    @Value("${keycloak-realm}")
    private String REALM;



    private final KeycloakClient keycloakClient;

    private final ObjectMapper mapper;

    public KeycloakUserService (KeycloakClient keycloakClient, ObjectMapper mapper) {
        this.keycloakClient = keycloakClient;
        this.mapper = mapper;
    }



    public List<UserViewDto> getByUsername(String username) throws  IOException {
        String response = keycloakClient.get("/admin/realms/"+REALM+"/users?username=" + username + "&enabled=true");
        List<UserViewDto> users =  mapper.readValue(response, new TypeReference<List<UserViewDto>>() {});
        if(users.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        return users;
    }

    public List<RoleViewDto> getUserRoles(String userId) throws  Exception {
        String response = keycloakClient.get("/admin/realms/" + REALM + "/users/" + userId + "/role-mappings/realm");
        return mapper.readValue(response, new TypeReference<List<RoleViewDto>>() {});
    }



}
