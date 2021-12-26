package com.orbirpinar.student.management.Keycloak.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbirpinar.student.management.Keycloak.Entity.KeycloakToken;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakTokenService;
import com.squareup.okhttp.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Component
@Getter
@Slf4j
public class KeycloakClientToken {


    @Value("${keycloak-client-id}")
    private String CLIENT_ID;

    @Value("${keycloak-client-secret}")
    private String CLIENT_SECRET;

    @Value("${keycloak-auth-server-url}")
    private String BASE_URL;

    @Value("${keycloak-realm}")
    private String REALM;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private KeycloakTokenService keycloakTokenService;




    public KeycloakToken token() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
            map.add("client_id",CLIENT_ID);
            map.add("client_secret",CLIENT_SECRET);
            map.add("grant_type","client_credentials");
            HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map,headers);
            ResponseEntity<KeycloakToken> response = restTemplate.
                    postForEntity(BASE_URL + "/realms/" + REALM + "/protocol/openid-connect/token",entity,KeycloakToken.class);
            KeycloakToken tokenObject = response.getBody();
            keycloakTokenService.saveOrUpdate(tokenObject);
            return tokenObject;
        }catch (Exception e) {
            log.error("<ERROR WHEN REQUESTING TOKEN ENDPOINT> {}",e.getCause().toString());
            throw new Exception("Error when requesting token endpoint");
        }
    }

    public String getToken() throws Exception {
        Optional<KeycloakToken> keycloakToken = keycloakTokenService.getById("keycloak");
        if(keycloakToken.isPresent()) {
            return keycloakToken.get().getAccess_token();
        }
        return token().getAccess_token();
    }


    public KeycloakToken getFromRefreshToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET +"&grant_type=refresh_token&refresh_token=" + getFromCache().getRefresh_token());
        Request request = new Request.Builder()
                .url(BASE_URL + "/realms/" + REALM + "/protocol/openid-connect/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        KeycloakToken keycloakToken = mapper.readValue(responseBody,KeycloakToken.class);
        return keycloakTokenService.saveOrUpdate(keycloakToken).get();
    }

    private KeycloakToken getFromCache() {
        return keycloakTokenService.getById("keycloak").get();
    }
}