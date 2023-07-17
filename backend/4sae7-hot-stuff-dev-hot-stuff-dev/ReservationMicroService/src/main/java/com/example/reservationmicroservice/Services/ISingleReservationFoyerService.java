package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import java.util.Date;
import java.util.List;

public interface ISingleReservationFoyerService {
    public SingleReservationFoyer addSingleReservationFoyer(SingleReservationFoyer srf, UserDTO user);
    public void deleteSingleReservationFoyer(Long id);
    public SingleReservationFoyer updateSingleReservationFoyer(SingleReservationFoyer srf,Long id);
    public SingleReservationFoyer retrieveSingleReservationFoyer(Long id);
    public List<SingleReservationFoyer> retrieveAllSingleReservationFoyer();
    public void cancelSingleReservationFoyer(Long id);

    public List<SingleReservationFoyer> retrieveSingleReservationFoyerBetweenTwoDates(Date dd, Date df);

    public List<SingleReservationFoyer> retrieveSingleReservationFoyerByUsername(String username);
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyer();
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyerByUsername(String username);
    public String GenerateQrCodeWithUsername (Long id);
}
