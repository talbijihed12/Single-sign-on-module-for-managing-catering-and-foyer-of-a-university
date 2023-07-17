package com.example.claimsmicroservice.usermcroservices;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String nom;
    Long userId;
    String email;

}
