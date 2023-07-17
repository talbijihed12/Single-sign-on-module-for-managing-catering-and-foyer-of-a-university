package com.example.authentificationmicroservice.Payload.Response;

import com.example.authentificationmicroservice.Entity.Role;
import com.example.authentificationmicroservice.Entity.SexeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponse {

    private SexeEnum gender;
    private Set<Role> roles;

    public UserDetailsResponse(Set<Role> roles, SexeEnum gender) {
        this.gender=gender;
        this.roles=roles;
    }
}
