package com.example.authentificationmicroservice.Payload.Request;

import com.example.authentificationmicroservice.Entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String username;
    private List<RoleEnum> roles;
}

