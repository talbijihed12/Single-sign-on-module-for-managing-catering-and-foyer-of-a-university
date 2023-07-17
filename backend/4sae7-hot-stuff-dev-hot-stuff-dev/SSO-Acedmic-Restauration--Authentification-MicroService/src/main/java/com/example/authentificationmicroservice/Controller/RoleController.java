package com.example.authentificationmicroservice.Controller;

import com.example.authentificationmicroservice.Services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list-role")
    public ResponseEntity listUsers() {
        return new ResponseEntity<>(roleService.listRole(), HttpStatus.OK);
    }

}

