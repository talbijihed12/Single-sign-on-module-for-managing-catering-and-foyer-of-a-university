package com.example.authentificationmicroservice.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private  String prenom;
    private List<String> roles;
    public JwtResponse(String accessToken, Long id, String username, String email,String prenom, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.prenom=prenom;
        this.roles = roles;
    }
}
