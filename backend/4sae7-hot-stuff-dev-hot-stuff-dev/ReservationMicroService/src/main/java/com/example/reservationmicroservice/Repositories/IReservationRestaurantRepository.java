package com.example.reservationmicroservice.Repositories;

import com.example.reservationmicroservice.Entity.Allergy;
import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface IReservationRestaurantRepository extends JpaRepository<ReservationRestaurant,Long> {
    public List<ReservationRestaurant> findReservationRestaurantsByDateStartBetween(Date dd, Date df);
    public List<ReservationRestaurant> findReservationRestaurantByDateEndBetween(Date dd,Date df);
    public List<ReservationRestaurant> findReservationRestaurantsByApproved(Boolean approved);
    public List<ReservationRestaurant> findReservationRestaurantsByUsername(String username);
    public List<ReservationRestaurant> findReservationRestaurantsByExpired(Boolean expired);
    public List<ReservationRestaurant> findReservationRestaurantsByExpiredAndUsername(Boolean expired,String username);
    public List<ReservationRestaurant> findReservationRestaurantsByExpiredAndApproved(Boolean expired, Boolean approved);
    public List<ReservationRestaurant> findReservationRestaurantsByAllergiesContains(Allergy allergy);

}
