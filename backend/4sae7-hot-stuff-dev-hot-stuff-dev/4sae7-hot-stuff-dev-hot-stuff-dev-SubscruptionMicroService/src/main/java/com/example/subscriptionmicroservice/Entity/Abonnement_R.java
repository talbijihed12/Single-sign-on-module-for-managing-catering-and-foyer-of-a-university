package com.example.subscriptionmicroservice.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

@Builder

public class Abonnement_R implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeRestaurant typeRestaurant;
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    private Boolean expired;
    private Float amount;
    private Integer nbJeton;
    public String username;







}
