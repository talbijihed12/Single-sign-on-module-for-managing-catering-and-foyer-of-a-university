package com.example.restorationmicroservice.Reservationmicroservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ReservationMicroService",url = "http://localhost:8095/rr")
public interface IReservationRestau {

    @GetMapping("/retrieveAllReservationRestaurantMS")
    public List<ReservationRestaurantDTO> getReservationRestaurant();

    @GetMapping("/retrieveReservationRestaurant/{id}")
     ReservationRestaurantDTO retrieveReservationRestaurant(@PathVariable("id") Long Id);

    @GetMapping("/findReservationRestaurantByAllergiesAndApprovedAndExpired/{allergy}/{approved}/{expired}")
    public List<ReservationRestaurantDTO> findReservationRestaurantByAllergiesAndApprovedAndExpired(@PathVariable("allergy")Allergy allergy, @PathVariable("approved")Boolean approved, @PathVariable("expired") Boolean expired);
}
