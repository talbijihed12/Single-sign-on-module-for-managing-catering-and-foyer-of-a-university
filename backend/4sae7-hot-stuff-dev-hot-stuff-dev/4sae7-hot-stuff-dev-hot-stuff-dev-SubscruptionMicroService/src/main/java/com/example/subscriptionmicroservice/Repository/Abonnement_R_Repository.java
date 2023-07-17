package com.example.subscriptionmicroservice.Repository;

import com.example.subscriptionmicroservice.Entity.Abonnement_F;
import com.example.subscriptionmicroservice.Entity.Abonnement_R;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Abonnement_R_Repository  extends JpaRepository<Abonnement_R, Long> {
    List<Abonnement_R> findAllByUsernameAndId(long idUser, long idAbonnement);

    List<Abonnement_R> findAllByExpiredAndUsername(boolean b, String username);

    Abonnement_R findByUsernameAndExpired(String Username, boolean b);

    List<Abonnement_R> findByExpiredAndUsername(boolean b, String username);
}
