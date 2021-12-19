package com.orbirpinar.student.management.Keycloak.Client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Slf4j
@Service
public class KeycloakClient{

    @Qualifier("KeycloakRestTemplate")
    private final RestTemplate keycloakRestTemplate;

    public KeycloakClient(RestTemplate keycloakRestTemplate) {
        this.keycloakRestTemplate = keycloakRestTemplate;
    }


    public String get(String endpoint) throws IOException {
        return keycloakRestTemplate.getForObject(endpoint,String.class);
    }

    public String post(String endpoint, String body) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body,headers);
        return keycloakRestTemplate.postForObject(endpoint,entity,String.class);
    }

    public String put(String endpoint, String body) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body,headers);
        ResponseEntity<String> response = keycloakRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);
        return response.getBody();
    }

    public void delete(String endpoint) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        keycloakRestTemplate.delete(endpoint);
    }

    public void deleteWithBody(String endpoint,String body) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body,headers);
        keycloakRestTemplate.exchange(endpoint, HttpMethod.DELETE,entity,String.class);
    }
}