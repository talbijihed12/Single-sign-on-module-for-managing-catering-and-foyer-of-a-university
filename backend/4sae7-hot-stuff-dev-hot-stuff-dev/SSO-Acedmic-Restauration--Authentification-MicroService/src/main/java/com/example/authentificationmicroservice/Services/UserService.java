package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.Role;
import com.example.authentificationmicroservice.Entity.User;
import com.example.authentificationmicroservice.Payload.Request.ResetPasswordRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequestDetails;
import com.example.authentificationmicroservice.Payload.Response.UserDetailsResponse;
import com.example.authentificationmicroservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IRoleService roleService;
    @Autowired
    AuthService authService;

    @Override
    public List<User> listUser() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepo.existsById(id))
            userRepo.deleteById(id);
        else {
            throw new NotFoundException("User with id: " + id + " not found");
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = this.findUserByUsername(resetPasswordRequest.getUsername());
        System.out.println(resetPasswordRequest.getPassword());
        String AccPassword=user.getPassword();
        System.out.println(AccPassword);
        if (encoder.matches(resetPasswordRequest.getPassword(),AccPassword)){
            String encodedPassword = encoder.encode(resetPasswordRequest.getNewPassword());
            user.setPassword(encodedPassword);
            this.userRepo.save(user);
        }
        else{
            throw new NotFoundException("password is incorrect");
        }

    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = this.findUserByUsername(updateUserRequest.getUsername());
        List<Role> roles = this.roleService.listRoleByRoleEnumsList(updateUserRequest.getRoles());
        user.setRoles(new HashSet<>(roles));
        this.userRepo.save(user);

    }
    @Override
    public void updateUserDetails(UpdateUserRequestDetails updateUserRequestDetails) {
        User user = this.findUserByUsername(updateUserRequestDetails.getUsername());
        user.setPrenom(updateUserRequestDetails.getPrenom());
        user.setGender(updateUserRequestDetails.getGender());
        user.setName(updateUserRequestDetails.getName());
        String encodedPassword = encoder.encode(updateUserRequestDetails.getPassword());
        user.setPassword(encodedPassword);
        this.userRepo.save(user);
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByNom(username).orElseThrow(() -> new NotFoundException("user not found with name -" + username));

    }

    @Override
    public User findUserByUserId(long UserId) {
        return userRepo.findById(UserId).orElseThrow(()-> new NotFoundException(("user with id : "+UserId+"not found")));
    }

    @Override
    public UserDetailsResponse loadUserDetails(String username){
        User user=userRepo.findByNom(username).orElseThrow(()->new NotFoundException("user with name"+username+"is not found"));
        return new UserDetailsResponse(user.getRoles(),user.getGender());
    }
    @Override
    public long countEnabledUsers() {
        return userRepo.countByEnabled(true);
    }





}

