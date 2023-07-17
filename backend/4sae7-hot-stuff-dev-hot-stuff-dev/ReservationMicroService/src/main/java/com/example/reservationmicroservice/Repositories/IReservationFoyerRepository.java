package com.example.reservationmicroservice.Repositories;

import com.example.reservationmicroservice.Entity.ReservationFoyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface IReservationFoyerRepository extends JpaRepository<ReservationFoyer,Long> {
    public List<ReservationFoyer> findReservationFoyerByDateStartBetween(Date dd, Date df);
    public List<ReservationFoyer> findReservationFoyersByDateEndBetween(Date dd,Date df);
    public List<ReservationFoyer> findReservationFoyersByApproved(Boolean approved);
    public List<ReservationFoyer> findReservationFoyersByUsername(String username);
    public List<ReservationFoyer> findReservationFoyersByExpired(Boolean expired);
    public List<ReservationFoyer> findReservationFoyersByExpiredAndUsername(Boolean expired,String username);

}
