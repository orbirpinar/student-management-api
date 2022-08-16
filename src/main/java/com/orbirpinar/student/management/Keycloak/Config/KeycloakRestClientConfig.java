package com.orbirpinar.student.management.Keycloak.Config;


import com.orbirpinar.student.management.Keycloak.Client.KeycloakClientToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class KeycloakRestClientConfig {


    @Value("${keycloak-auth-admin-url}")
    private String BASE_URL;

    @Autowired
    private KeycloakClientToken keycloakClientToken;


    @Bean
    @Qualifier("KeycloakRestTemplate")
    public RestTemplate keycloakRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new KeycloakRestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new RootUriTemplateHandler(BASE_URL));


        return restTemplate;
    }


    public class KeycloakRestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {


        @SneakyThrows
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException, IOException {
            request.getHeaders().add("accept", "application/json");
            request.getHeaders().add("content-type", "application/json");

            String token = keycloakClientToken.getToken();
            request.getHeaders().add("authorization", "Bearer " + token);
            ClientHttpResponse response = execution.execute(request, body);

            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                keycloakClientToken.token();
                request.getHeaders().remove(HttpHeaders.AUTHORIZATION);
                request.getHeaders().add("authorization", "Bearer " + keycloakClientToken.getToken());
                response = execution.execute(request, body);
            }

            return response;

        }
    }
}
