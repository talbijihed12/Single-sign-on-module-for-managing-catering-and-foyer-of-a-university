package com.example.foyermicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idR ;
    @NotNull(message = "le nombre de chambre ne doit pas etre null")
    Integer number ;
    @Size(min = 2, max = 10)
    String floor ;
    @NotNull(message = "le nombre de place  ne doit pas etre 0")


    Integer nbrPlace;
    Integer placesOcc ;
    Boolean cleaningRoom;

    @JsonIgnore
    @ManyToOne
    Block block;
}