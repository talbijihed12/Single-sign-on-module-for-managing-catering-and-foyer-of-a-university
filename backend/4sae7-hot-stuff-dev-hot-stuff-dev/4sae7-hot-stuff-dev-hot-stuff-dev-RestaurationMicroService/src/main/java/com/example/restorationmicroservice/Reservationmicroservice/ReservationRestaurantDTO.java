package com.example.restorationmicroservice.Reservationmicroservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRestaurantDTO {
    Long idR;
    List<Allergy> allergies ;
    Date dateStart;
    Date dateEnd;
    String username;

}
