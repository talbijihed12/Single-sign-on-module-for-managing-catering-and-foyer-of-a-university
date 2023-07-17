package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.UserMicroService.UserDTO;

import java.util.Date;
import java.util.List;

public interface IReservationRestaurantService {
    public ReservationRestaurant addReservationRestaurant(ReservationRestaurant rr);
    public ReservationRestaurant addReservationRestaurant(ReservationRestaurant rr, UserDTO user);
    public void deleteReservationRestaurant(Long id);
    public ReservationRestaurant updateReservationRestaurant(ReservationRestaurant rr,Long id);
    public ReservationRestaurant retrieveReservationRestaurant(Long id);
    public List<ReservationRestaurant> retrieveAllReservationRestaurant();
    public void approveReservationRestaurant(Long id);
    public List<ReservationRestaurant> retrieveReservationRestaurantBetweenTwoDates(Date dd, Date df);
    public List<ReservationRestaurant> retrieveApprovedReservationRestaurantBetweenTwoDates(Date dd, Date df);
    public List<ReservationRestaurant> retrieveReservationRestaurantByUsername(UserDTO user);
    public List<ReservationRestaurant> retrieveActiveReservationRestaurant();
    public List<ReservationRestaurant> retrieveActiveReservationRestaurantByUsername(UserDTO user);
    public String GenerateQrCodeWithUsername (Long id);


}
