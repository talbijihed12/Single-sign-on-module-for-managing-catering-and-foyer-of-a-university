package com.example.restorationmicroservice.DTO;

import com.example.restorationmicroservice.Entity.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private VoteType voteType;
    private String username;
    private Long id;
}
