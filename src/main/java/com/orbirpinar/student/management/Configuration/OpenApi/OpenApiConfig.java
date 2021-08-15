package com.orbirpinar.student.management.Configuration.OpenApi;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SecurityScheme(name = "SWAGGER_UI_LOCAL",type = SecuritySchemeType.OAUTH2,
//
//        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
//                authorizationUrl = "${keycloak-base-url}/${keycloak-realm}/protocol/openid-connect/auth",
//                tokenUrl = "${keycloak-base-url}/${keycloak-realm}/protocol/openid-connect/token",
//                refreshUrl = "${keycloak-base-url}/${keycloak-realm}/protocol/openid-connect/token"
//        )))
@Configuration
@SecurityScheme(name = "SWAGGER_UI_DEV",type = SecuritySchemeType.OAUTH2,

        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${keycloak-auth-server-url}/realms/${keycloak-realm}/protocol/openid-connect/auth",
                tokenUrl = "${keycloak-auth-server-url}/realms/${keycloak-realm}/protocol/openid-connect/token",
                refreshUrl = "${keycloak-auth-server-url}/realms/${keycloak-realm}/protocol/openid-connect/token",
                scopes = {@OAuthScope(name = "openid")}
        )))
public class OpenApiConfig {


    @Value("${swagger.server.url}")
    private String SERVER_URL;

    @Bean
    public OpenAPI customOpenApi() {
        final String swaggerDev = "SWAGGER_UI_DEV";

        return new OpenAPI()
                .addServersItem(new Server().url(SERVER_URL))
                .addSecurityItem(new SecurityRequirement().addList(swaggerDev))
                .info(new Info().title("Student Management API")
                        .description("REST API build with Spring Boot")
                        .version("v1")
                        .contact(new Contact().name("Orhan Birpınar").email("orbirpinar@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}