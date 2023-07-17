package com.example.subscriptionmicroservice.Entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User {
    private Long userId;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private boolean enabled;
    private long soldeJeton;
    private long points;


}
