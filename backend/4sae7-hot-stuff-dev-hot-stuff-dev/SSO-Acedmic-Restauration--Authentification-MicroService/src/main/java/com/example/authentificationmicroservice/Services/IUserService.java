package com.example.authentificationmicroservice.Services;

import com.example.authentificationmicroservice.Entity.User;
import com.example.authentificationmicroservice.Payload.Request.ResetPasswordRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequestDetails;
import com.example.authentificationmicroservice.Payload.Response.UserDetailsResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    List<User> listUser();

    void deleteUser(Long id);


    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void updateUser(UpdateUserRequest updateUserRequest);





    void updateUserDetails(UpdateUserRequestDetails updateUserRequestDetails);

    User findUserByUsername(String username);
    User findUserByUserId(long UserId);


    UserDetailsResponse loadUserDetails(String username);

    long countEnabledUsers();

}
