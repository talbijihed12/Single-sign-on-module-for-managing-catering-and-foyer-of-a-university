package com.example.subscriptionmicroservice.Service;

import com.example.subscriptionmicroservice.Entity.Room;
import com.example.subscriptionmicroservice.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value="AUTHENTIFICATIONMICROSERVICE",url = "http://localhost:8093/user")
public interface MicroSU {

        @GetMapping("/listUser")
        List<User> readUsers();

        @GetMapping("/findUserById/{userId}")
        User findUserByUserId(@PathVariable("userId") long userId);

        @PutMapping("/updateDetails")
        User updateUserDetails(@RequestBody User user);

}
