package com.example.subscriptionmicroservice.Entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Room {
    Long idR ;
    Integer number ;
    String floor ;
    Integer nbrPlace;
    Integer placesOcc ;
}
