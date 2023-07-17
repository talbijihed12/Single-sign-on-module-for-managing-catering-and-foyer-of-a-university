package com.example.reservationmicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class SingleReservationFoyer implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id ;

        Date dateStart;

        Date dateEnd;

        String username;

        Long idRoom;
        Boolean expired = false;

    }

