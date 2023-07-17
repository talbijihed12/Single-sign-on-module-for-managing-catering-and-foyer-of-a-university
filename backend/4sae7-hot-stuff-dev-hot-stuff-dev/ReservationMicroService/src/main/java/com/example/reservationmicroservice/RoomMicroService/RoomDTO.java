package com.example.reservationmicroservice.RoomMicroService;


import lombok.Data;

@Data
public class RoomDTO {
    Long idR ;
    Integer number ;
    String floor ;
    String block;
}
