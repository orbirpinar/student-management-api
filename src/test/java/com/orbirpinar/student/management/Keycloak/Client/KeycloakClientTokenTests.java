package com.orbirpinar.student.management.Keycloak.Client;


import com.orbirpinar.student.management.Keycloak.Entity.KeycloakToken;
import com.orbirpinar.student.management.Keycloak.Service.KeycloakTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeycloakClientTokenTests {


    @Autowired
    private KeycloakClientToken keycloakClientToken;

    @MockBean
    private KeycloakTokenService keycloakTokenService;

    @Value("${keycloak-client-id}")
    private String CLIENT_ID;

    @Value("${keycloak-client-secret}")
    private String CLIENT_SECRET;

    @Value("${keycloak-auth-server-url}")
    private String BASE_URL;

    @Value("${keycloak-realm}")
    private String REALM;

    @Test
    public void shouldReturnHttpStatusOk_WhenClientIdAndClientSecretIsCorrect() throws Exception {
        assertNotNull(keycloakClientToken.token());
    }

    @Test
    public void getTokenMethodShouldReturnAccessTokenAndPersistInCache() throws Exception {
        KeycloakToken keycloakToken = new KeycloakToken();
        keycloakToken.setAccess_token("123");
        keycloakToken.setExpires_in(123);
        keycloakToken.setId("keycloak");
        keycloakToken.setRefresh_token("1234");
        Mockito.when(keycloakTokenService.getById("keycloak")).thenReturn(java.util.Optional.of(keycloakToken));
        assertEquals("123",keycloakClientToken.getToken());
    }
}
