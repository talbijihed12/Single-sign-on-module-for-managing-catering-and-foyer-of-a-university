package com.example.claimsmicroservice.dto;

import com.example.claimsmicroservice.entities.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimDto {
    Status status;
}
