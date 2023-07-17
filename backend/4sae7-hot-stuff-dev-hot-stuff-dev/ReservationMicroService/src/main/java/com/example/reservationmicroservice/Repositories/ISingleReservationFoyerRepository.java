package com.example.reservationmicroservice.Repositories;

import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface ISingleReservationFoyerRepository extends JpaRepository<SingleReservationFoyer,Long> {
    public List<SingleReservationFoyer> findSingleReservationFoyersByDateStartBetween(Date dd, Date df);
    public List<SingleReservationFoyer> findSingleReservationFoyerByDateEndBetween(Date dd,Date df);
    public List<SingleReservationFoyer> findSingleReservationFoyersByUsername(String username);
    public List<SingleReservationFoyer> findSingleReservationFoyersByExpired(Boolean expired);
    public List<SingleReservationFoyer> findSingleReservationFoyersByExpiredAndUsername(Boolean expired,String username);
}
