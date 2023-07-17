package com.example.authentificationmicroservice.Controller;

import com.example.authentificationmicroservice.Payload.Request.KeycloakLoginRequest;
import com.example.authentificationmicroservice.Payload.Request.KeycloakUser;
import com.example.authentificationmicroservice.Payload.Request.LoginRequest;
import com.example.authentificationmicroservice.Payload.Response.JwtResponse;
import com.example.authentificationmicroservice.Services.KeyCloakService;
import com.example.authentificationmicroservice.config.KeycloakConfig;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.authentication.ClientCredentialsProvider;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping( "/keycloakUser")
@CrossOrigin("*")
public class KeyCloakController {





    KeyCloakService service;
    @PostMapping("/signup")

    public UserRepresentation addUser(@Valid @RequestBody KeycloakUser keycloakUser){

        return service.addUser(keycloakUser);
    }

   /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        // Create KeycloakAuthenticationToken with user credentials
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());

        // Authenticate user with Keycloak
        Authentication auth = keycloakAuthenticationProvider.authenticate(authToken);

        // Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate access token
        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) auth.getCredentials();
        AccessToken token = keycloakSecurityContext.getToken();
        String accessToken = token.getTokenString();

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }*/


    @PostMapping("/admin/addUserByAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addUserByAdmin(@Valid @RequestBody KeycloakUser keycloakUser){
        service.addUserByAdmin(keycloakUser);
        return ResponseEntity.status(HttpStatus.OK).body("User Added Successfully");
    }



    @GetMapping("/admin/details/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserRepresentation getUser(@PathVariable("userId") String userId){
        UserRepresentation user = service.getUser(userId);
        return user;
    }
    @GetMapping("/admin/findAll")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserRepresentation> getUsers(){
        List<UserRepresentation> user = service.getUsers();
        return user;
    }
    @PutMapping("/admin/addRoleToUser/{userName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String assignRealmRolesToUser(@RequestBody KeycloakUser keycloakUser, @PathVariable("userName") String userName){
    service.assignRealmRolesToUser(keycloakUser,userName);
    return "role assigned to user";
    }

    @PutMapping( "/admin/UpdateUserByAdmin/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserByAdmin(@Valid @PathVariable("userId") String userId, @RequestBody KeycloakUser keycloakUser){

        service.updateUserByAmin(userId, keycloakUser);
        return "User Details Updated Successfully.";
    }
    @PutMapping( "/UpdateUser")
    public String updateUser(@Valid @RequestBody KeycloakUser keycloakUser,KeycloakAuthenticationToken authenticationToken){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) authenticationToken.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String userId = context.getToken().getSubject();
        System.out.println(userId);
        service.updateUser(userId, keycloakUser);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping( "/deleteAccount/{userId}")
    public String deleteUserByAdmin(@PathVariable("userId") String userId){

        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }
    @DeleteMapping( "/deleteMyAccount")
    public String deleteUser(KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String userId = context.getToken().getSubject();
        service.deleteMyAccount(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping( "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping( "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }
    @GetMapping("/hello")
        public String hello (KeycloakAuthenticationToken auth){
            KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
            KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
            String username = context.getToken().getPreferredUsername();
            String email = context.getToken().getEmail();
            String userId=context.getToken().getSubject();
            return "Hello, " + username + " (" + email + ")" + userId ;

    }
    @GetMapping("/jwt")
    @ResponseBody
    public Map<String,String> map (HttpServletRequest request){
        KeycloakAuthenticationToken token=(KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal= (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext=principal.getKeycloakSecurityContext();
        Map<String,String> map=new HashMap<>();
        map.put("access_token",keycloakSecurityContext.getTokenString());
        return map;
    }



}
