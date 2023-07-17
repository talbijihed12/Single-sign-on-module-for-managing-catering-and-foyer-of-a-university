package com.example.authentificationmicroservice.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message="new password is required")
    private String newPassword;
    @NotBlank(message = "Password is required")
    private String password;
}
