package com.example.authentificationmicroservice.Payload.Request;

import com.example.authentificationmicroservice.Entity.RoleEnum;
import com.example.authentificationmicroservice.Entity.SexeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String prenom;
    @NotBlank
    private String name;
    @NotBlank
    private SexeEnum gender;
    private RoleEnum role;
}
