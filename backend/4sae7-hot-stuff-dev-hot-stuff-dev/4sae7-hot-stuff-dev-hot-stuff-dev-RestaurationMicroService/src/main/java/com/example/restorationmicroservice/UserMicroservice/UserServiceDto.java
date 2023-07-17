package com.example.restorationmicroservice.UserMicroservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="AuthentificationMicroService",url = "http://localhost:8093/user")
public interface UserServiceDto {


    @GetMapping("/listUser")
    List<UserDto> listUsers();

    @GetMapping("/findUserByUsername/{username}")
    UserDto findUserByUsername(@PathVariable("username") String username);
    //public User getCurrentUser();
}
