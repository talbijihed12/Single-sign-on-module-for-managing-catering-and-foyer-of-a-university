package com.example.reservationmicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationRestaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long idR ;
    Date dateStart;
    Date dateEnd;
    @Enumerated(EnumType.STRING)
    Pension pension ;
    @ElementCollection(targetClass = Allergy.class)
    @JoinTable(name = "tblAllergy", joinColumns = @JoinColumn(name = "idR"))
    @Column(name = "allergy", nullable = false)
    @Enumerated(EnumType.STRING)
    List<Allergy> allergies ;
    Integer nbrPerson;
    String nameGrp ;
    Boolean approved = false;
    Boolean expired = false;
    String username;
}