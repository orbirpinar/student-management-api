package com.orbirpinar.student.management.Keycloak.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbirpinar.student.management.Keycloak.Entity.KeycloakToken;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakTokenService;
import com.squareup.okhttp.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Getter
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


    public KeycloakToken token() throws  IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        com.squareup.okhttp.RequestBody body = RequestBody.create(mediaType, "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials");
        Request request = new Request.Builder()
                .url(BASE_URL + "/realms/" + REALM + "/protocol/openid-connect/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        KeycloakToken keycloakToken = mapper.readValue(responseBody,KeycloakToken.class);
        keycloakTokenService.saveOrUpdate(keycloakToken);
        return keycloakToken;
    }

    public String getToken() throws IOException {
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