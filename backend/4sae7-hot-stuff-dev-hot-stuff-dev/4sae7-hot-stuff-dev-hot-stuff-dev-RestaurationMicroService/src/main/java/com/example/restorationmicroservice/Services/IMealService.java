package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.Entity.*;
import com.example.restorationmicroservice.Reservationmicroservice.ReservationRestaurantDTO;

import java.util.Date;
import java.util.List;

public interface IMealService {
    //void uploadFile(MultipartFile[] files, Long id);
     Meal affectMealToMenu(Long idMeal, TypeMenu typeMenu, TypeEmplacement typeEmplacement);
     List<Meal> getmealByDate(Date datestart);
     Integer nbVoteodMeal(Integer voteCount);
    Meal findMealByMenuTypeMenu(TypeMenu typeMenu);
     Meal findMealByMenuTypeMenu2(TypeMenu typeMenu);
   // void EnvoyerNotif(ReservationRestaurantDTO reservationRestaurantDTO,String username);
    Meal affectMealToMenuu(Long idMeal, Menu menu);
}
