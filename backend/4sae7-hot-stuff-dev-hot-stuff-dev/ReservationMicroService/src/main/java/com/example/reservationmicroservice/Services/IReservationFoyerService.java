package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.Entity.ReservationFoyer;

import java.util.Date;
import java.util.List;


public interface IReservationFoyerService {
    public ReservationFoyer addReservationFoyer(ReservationFoyer rf,String username);
    public ReservationFoyer addReservationFoyer(ReservationFoyer rf);
    public void deleteReservationFoyer(Long id);
    public ReservationFoyer updateReservationFoyer(ReservationFoyer rf,Long id);
    public ReservationFoyer retrieveReservationFoyer(Long id);
    public List<ReservationFoyer> retrieveAllReservationFoyer();
    public void approveReservationFoyer(Long id, List<Long> listIdRoom);
    public void cancelReservationFoyer(Long id);
    public List<ReservationFoyer> retrieveReservationFoyerBetweenTwoDates(Date dd,Date df);
    public List<ReservationFoyer> retrieveApprovedReservationFoyerBetweenTwoDates(Date dd, Date df);
    public List<ReservationFoyer> retrieveReservationFoyerByUsername(String username);
    public List<ReservationFoyer> retrieveActiveReservationFoyer();
    public List<ReservationFoyer> retrieveActiveReservationFoyerByUsername(String username);
    public String GenerateQrCodeWithUsername (Long id);
    public String GenerateQrCodeWithoutUsername (Long id);

}
