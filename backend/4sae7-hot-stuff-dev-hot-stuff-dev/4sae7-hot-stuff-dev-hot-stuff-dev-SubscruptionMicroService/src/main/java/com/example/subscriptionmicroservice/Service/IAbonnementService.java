package com.example.subscriptionmicroservice.Service;

import com.example.subscriptionmicroservice.Entity.Abonnement_F;
import com.example.subscriptionmicroservice.Entity.Abonnement_R;
import com.example.subscriptionmicroservice.Entity.TypeFoyer;
import com.example.subscriptionmicroservice.Entity.User;

import java.util.Date;
import java.util.List;

public interface IAbonnementService {


    Date getCurrentTimeUsingCalendar();


    void deleteAbonnementF(long id);


    List<Abonnement_F> retrieveAllF();



    int addAbonnementF(Abonnement_F abonnement_f, String username);

    Abonnement_F modifyAbonnementF(Abonnement_F abonnement_f);

    int addAbonnementR(Abonnement_R abonnement_r, String username);


    Abonnement_R modifyAbonnementR(Abonnement_R abonnement_r, long id);

    void deleteAbonnementR(long id);


    List<Abonnement_R> retrieveAllR();

    boolean useJeton(String username, int nbJeton);

    int addJeton(String username, int nbJeton);

    Long countByColor(TypeFoyer tf);

    void email(long idUser);


    ;
}
