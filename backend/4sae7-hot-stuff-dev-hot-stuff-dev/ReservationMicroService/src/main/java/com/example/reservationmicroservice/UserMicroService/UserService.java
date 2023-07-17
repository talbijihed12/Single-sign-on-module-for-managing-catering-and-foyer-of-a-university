package com.example.reservationmicroservice.UserMicroService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "AUTHENTIFICATIONMICROSERVICE",url = "http://localhost:8093/user")
public interface UserService {
    @GetMapping("/listUser")
    List<UserDTO> readItems();
    @GetMapping("/retrieveUser/{username}")
    UserDTO retrieveUserInfo(@PathVariable("username") String username);
}
