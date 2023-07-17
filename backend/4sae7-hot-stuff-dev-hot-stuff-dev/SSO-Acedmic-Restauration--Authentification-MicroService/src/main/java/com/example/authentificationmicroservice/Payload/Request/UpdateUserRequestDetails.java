package com.example.authentificationmicroservice.Payload.Request;

import com.example.authentificationmicroservice.Entity.SexeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDetails {
    @NotBlank(message = "Username is required")
    private String username;

    @NotEmpty
    @NonNull
    private String prenom;
    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private SexeEnum gender;
    @NotEmpty(message = "Email is required")
    private String name;
}
