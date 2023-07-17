package com.example.subscriptionmicroservice.Repository;


import com.example.subscriptionmicroservice.Entity.Abonnement_F;
import com.example.subscriptionmicroservice.Entity.Abonnement_R;
import com.example.subscriptionmicroservice.Entity.TypeFoyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Abonnement_F_Repository extends JpaRepository<Abonnement_F, Long> {


    Long countAbonnement_FSByTypeFoyer(TypeFoyer typeFoyer);



    List<Abonnement_F> findAllByExpiredAndUsername(boolean expired, String username);

    Abonnement_F findByExpired(boolean b);


    List<Abonnement_F> findByExpiredAndUsername(boolean b, String username);

    List<Abonnement_R> findByUsername(String username);


}