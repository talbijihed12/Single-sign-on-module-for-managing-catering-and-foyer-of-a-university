package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.*;
import com.example.authentificationmicroservice.Payload.Request.LoginRequest;
import com.example.authentificationmicroservice.Payload.Request.SignUpRequest;
import com.example.authentificationmicroservice.Payload.Response.AuthentificationResponse;
import com.example.authentificationmicroservice.Payload.Response.JwtResponse;
import com.example.authentificationmicroservice.Repositories.RoleRepository;
import com.example.authentificationmicroservice.Repositories.UserRepo;
import com.example.authentificationmicroservice.Repositories.VerificationTokenRepo;
import com.example.authentificationmicroservice.Security.JwtProvider;
import com.example.authentificationmicroservice.Security.Service.UserDetailsImpl;
import com.example.authentificationmicroservice.utils.exceptions.BadRequestException;
import com.example.authentificationmicroservice.utils.exceptions.EmailException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepo verificationTokenRepo;
    private final UserRepo userRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    /*@Transactional
    public void signup(RegisterRequest registerRequest){
        User user=new User();
        user.setNom(registerRequest.getNom());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);
        user.setPrenom(registerRequest.getPrenom());
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationMail("Please Activate your account",
                user.getEmail(),"Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/auth/accountVerification/" +token));
    }*/
    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);
        return token;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Optional<User> user = userRepo.findByNom(loginRequest.getUsername());
        if (!user.get().isEnabled()) {
            throw new BadRequestException("you should activate your email");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPrenom(),
                roles);
    }





    @Override
    public void registerUser(SignUpRequest signUpRequest) {
        if (userRepo.existsByNom(signUpRequest.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

         if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }
        User user = User.builder().nom(signUpRequest.getUsername()).email(signUpRequest.getEmail()).prenom(signUpRequest.getPrenom()).name(signUpRequest.getName()).gender(signUpRequest.getGender()).password(encoder.encode(signUpRequest.getPassword())).build();
        Set roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(RoleEnum.ETUDIANT);
        if (!userRole.isPresent()) throw new RuntimeException("Error: Role is not found.");
        roles.add(userRole.get());
        user.setRoles(roles);
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationMail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8093/auth/accountVerification/" +token));

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken =verificationTokenRepo.findByToken(token);
        verificationToken.orElseThrow(()->new EmailException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }



    @Override
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String nom = verificationToken.getUser().getNom();
        User user =userRepo.findByNom(nom).orElseThrow(()->new EmailException("user with name"+nom+" is not found"));
        user.setEnabled(true);
        userRepo.save(user);
    }

    /*public AuthentificationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthentificationResponse(authenticationToken, loginRequest.getUsername(),loginRequest.getPassword());

    }*/
    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByNom(username).orElseThrow(() -> new UsernameNotFoundException("User name not found -" + username));

    }


    @Override
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

    }


}