package com.example.claimsmicroservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SmsRequest {

    @NotBlank
    private final String phoneNumber; // destination

    @NotBlank
    private final String message;

    /*public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }*/


}
