package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.User;
import com.example.authentificationmicroservice.Entity.VerificationToken;
import com.example.authentificationmicroservice.Payload.Request.LoginRequest;
import com.example.authentificationmicroservice.Payload.Request.SignUpRequest;
import com.example.authentificationmicroservice.Payload.Response.JwtResponse;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    void registerUser(SignUpRequest signUpRequest);

    void fetchUserAndEnable(VerificationToken verificationToken);

    User getCurrentUser();

    boolean isLoggedIn();
}
