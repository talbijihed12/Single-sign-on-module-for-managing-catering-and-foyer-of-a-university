package com.example.reservationmicroservice.Entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationFoyer   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date dateStart;
    Date dateEnd;
    String nameGrp;
    Boolean approved=false;
    Boolean expired = false;
    int nbrFemale;
    int nbrMale;
    int nbrMixte;
    String username;

    @ElementCollection
    List<Long> roomIdList;
}
