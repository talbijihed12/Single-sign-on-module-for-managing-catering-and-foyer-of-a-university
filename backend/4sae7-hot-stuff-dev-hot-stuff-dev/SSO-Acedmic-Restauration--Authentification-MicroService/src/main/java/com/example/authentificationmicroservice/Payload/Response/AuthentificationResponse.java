package com.example.authentificationmicroservice.Payload.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthentificationResponse {
    private String authenticationToken;
    private String Nom;
    private String email;

    public AuthentificationResponse(String authenticationToken, String nom, String email) {
        this.authenticationToken = authenticationToken;
        Nom = nom;
        this.email = email;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
