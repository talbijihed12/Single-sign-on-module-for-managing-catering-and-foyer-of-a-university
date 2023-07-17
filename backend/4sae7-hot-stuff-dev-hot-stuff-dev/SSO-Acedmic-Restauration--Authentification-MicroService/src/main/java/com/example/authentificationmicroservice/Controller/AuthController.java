package com.example.authentificationmicroservice.Controller;

import com.example.authentificationmicroservice.Payload.Request.LoginRequest;
import com.example.authentificationmicroservice.Payload.Request.SignUpRequest;
import com.example.authentificationmicroservice.Payload.Response.AuthentificationResponse;
import com.example.authentificationmicroservice.Payload.Response.JwtResponse;
import com.example.authentificationmicroservice.Repositories.UserRepo;
import com.example.authentificationmicroservice.Services.AuthService;
import com.example.authentificationmicroservice.utils.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody SignUpRequest signUpRequest){
        authService.registerUser(signUpRequest);
        return new ResponseEntity<>("User Registration successfully", HttpStatus.OK);

    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount (@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
    }
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);


    }




}

