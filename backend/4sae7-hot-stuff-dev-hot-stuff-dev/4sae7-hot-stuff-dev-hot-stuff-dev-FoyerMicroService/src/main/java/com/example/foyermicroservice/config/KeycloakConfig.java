package com.example.foyermicroservice.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
    @Bean
    KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory){
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }

    static Keycloak keycloak;
    final static String serverUrl = "http://localhost:8180/auth";
    public final static String realm = "SSO-Acedmic-Restauration";
    final static String clientId = "SSO-Acedmic-Restauration--Academic-Home-MicroService";
    final static String clientSecret = "7ad3f39e-545e-4721-b5f6-34f4e2701152";
    final static String userName = "jihed";
    final static String password = "jihed";
    public KeycloakConfig() {
    }
@Bean
    public static Keycloak getInstance() {
    keycloak = KeycloakBuilder.builder()
            .serverUrl(serverUrl)
            .realm(realm)
            .grantType(OAuth2Constants.PASSWORD)
            .username(userName)
            .password(password)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .resteasyClient(new ResteasyClientBuilder()
                    .connectionPoolSize(10)
                    .build())
            .build();
    return keycloak;
}






}



