package com.orbirpinar.student.management.Keycloak.Service;

import com.orbirpinar.student.management.Api.Role.DTO.RoleViewDto;
import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.User.Repository.UserRepository;
import com.orbirpinar.student.management.Api.User.Service.UserService;
import com.orbirpinar.student.management.Keycloak.dto.KeycloakUserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KeycloakUserService {

    @Value("${keycloak-realm}")
    private String REALM;


    @Autowired
    @Qualifier("KeycloakRestTemplate")
    private final RestTemplate restTemplate;

    private final UserRepository userRepository;



    public KeycloakUserService(@Qualifier("KeycloakRestTemplate") RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }


    public List<UserViewDto> getByUsername(String username) {
        ResponseEntity<UserViewDto[]> response = restTemplate.getForEntity(
                "/users?username=" + username + "&enabled=true", UserViewDto[].class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Unknown Error");
        }
        if (Objects.requireNonNull(response.getBody()).length == 0) {
            throw new RuntimeException("User not found");
        }
        return Arrays.stream(response.getBody()).toList();
    }

    public List<RoleViewDto> getUserRoles(String userId) {
        ResponseEntity<RoleViewDto[]> response = restTemplate.getForEntity("/users/" + userId + "/role-mappings/realm",RoleViewDto[].class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Unknown Error");
        }
        if (Objects.requireNonNull(response.getBody()).length == 0) {
            throw new RuntimeException("User not found");
        }
        return Arrays.stream(response.getBody()).toList();
    }

    public void createOrUpdateUser(KeycloakUserCreateDto request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if(existingUser.isPresent()) {
            String userId = existingUser.get().getId();
            restTemplate.put("/users/" + userId,request,Void.class);
        }else {
            ResponseEntity<Void> response = restTemplate.postForEntity("/users", request, Void.class);
            if(response.getStatusCode() != HttpStatus.CREATED) {
                throw new RuntimeException("User not created contact with admin!");
            }
        }
    }


}
