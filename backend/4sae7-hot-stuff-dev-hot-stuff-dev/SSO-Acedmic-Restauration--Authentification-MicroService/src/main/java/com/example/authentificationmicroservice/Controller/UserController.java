package com.example.authentificationmicroservice.Controller;

import com.example.authentificationmicroservice.Entity.User;
import com.example.authentificationmicroservice.Payload.Request.ResetPasswordRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequest;
import com.example.authentificationmicroservice.Payload.Request.UpdateUserRequestDetails;
import com.example.authentificationmicroservice.Payload.Response.MessageResponse;
import com.example.authentificationmicroservice.Payload.Response.UserDetailsResponse;
import com.example.authentificationmicroservice.Security.Service.UserDetailsImpl;
import com.example.authentificationmicroservice.Services.IUserService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HEAD;

@RestController
@RequestMapping("/user")
public class UserController {

    KeycloakSecurityContext keycloakSecurityContext;
    @Autowired
    IUserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listUser")
    public ResponseEntity listUsers() {

        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @GetMapping("/userCurret")
    public String getUser() {
        return keycloakSecurityContext.getToken().getSubject();

    }





    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.updateUser(updateUserRequest);
        return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
    }
    @PreAuthorize("hasAnyRole()")
    @PutMapping("/updateDetails")
    public ResponseEntity updateUserDetails(@RequestBody UpdateUserRequestDetails userRequestDetails){
        this.userService.updateUserDetails(userRequestDetails);
        return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
    }
    @GetMapping("/findUser/{username}")
    public ResponseEntity findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }
   @GetMapping("/userDetails/{username}")
    public UserDetailsResponse loadUserDetailsByUsername(@PathVariable String username){
        return userService.loadUserDetails(username);   }
    @GetMapping("numberOfUsersActivated")
        public ResponseEntity countEnabledUsers(){
            return new ResponseEntity<>(userService.countEnabledUsers(),HttpStatus.OK);
        }
    @GetMapping("findUserById/{userId}")
    public ResponseEntity findUserById(@PathVariable long userId){
        return new ResponseEntity<>(userService.findUserByUserId(userId),HttpStatus.OK);
    }
    @PutMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        this.userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
    }



}



