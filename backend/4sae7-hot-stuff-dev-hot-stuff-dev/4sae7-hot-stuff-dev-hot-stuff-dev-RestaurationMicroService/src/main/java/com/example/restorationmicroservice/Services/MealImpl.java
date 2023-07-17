package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.EmailService.IEmailService;
import com.example.restorationmicroservice.Entity.*;
import com.example.restorationmicroservice.Exceptions.MealNotFoundException;
import com.example.restorationmicroservice.Repository.MealRepo;
import com.example.restorationmicroservice.Repository.MenuRepo;
import com.example.restorationmicroservice.Reservationmicroservice.Allergy;
import com.example.restorationmicroservice.Reservationmicroservice.IReservationRestau;
import com.example.restorationmicroservice.Reservationmicroservice.ReservationRestaurantDTO;
import com.example.restorationmicroservice.UserMicroservice.UserDto;
import com.example.restorationmicroservice.UserMicroservice.UserServiceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class MealImpl implements IService<Meal>,IMealService {
    private MealRepo mealRepo;
    private MenuRepo menuRepo;
    private UserServiceDto userServiceDto;
    private IEmailService iEmailService;
    private IReservationRestau iReservationRestau;


    @Override
    public Meal Create(Meal meal ) {
        return mealRepo.save(meal);
    }
    @Override
    public List<Meal> Read() {

        return mealRepo.findAll();
    }
    @Override
    public Meal Update( Long ID ,Meal T) {
        return mealRepo.save(T);
    }
    @Override
    public void Delete(Long ID) {
         mealRepo.deleteById(ID);
    }

    @Override
    public Meal getOne(Long ID) {
        return mealRepo.findById(ID).orElseThrow(() -> new MealNotFoundException("Meal Not Found with this ID "));
    }

    @Override
    public Meal affectMealToMenu(Long idMeal, TypeMenu typeMenu, TypeEmplacement typeEmplacement) {
        Meal meall = mealRepo.findById(idMeal).orElseThrow(() -> new MealNotFoundException("Meal Not Found with this ID "));
        Menu menu = menuRepo.findMenuByTypeMenuAndTypeEmplacement(typeMenu,typeEmplacement);
        meall.setMenu(menu);
        return mealRepo.save(meall);
    }
    @Override
    public List<Meal> getmealByDate(Date datestart) {
        return mealRepo.findMealByDatestart(datestart);
    }

    @Override
    public Integer nbVoteodMeal(Integer voteCount) {
        return mealRepo.countMealsByVoteCount(voteCount);
    }

    @Override
    public Meal findMealByMenuTypeMenu(TypeMenu typeMenu) {
        return mealRepo.findMealByMenuTypeMenu(TypeMenu.Standard);
    }
    @Override
    public Meal findMealByMenuTypeMenu2(TypeMenu typeMenu) {
        return mealRepo.findMealByMenuTypeMenu(TypeMenu.Personnalisé);
    }

    @Transactional
    public Meal affectMealToMenuu( Long idMeal, Menu menu) {
        Meal meall = mealRepo.findById(idMeal).orElseThrow(() -> new MealNotFoundException("Meal Not Found with this ID "));
        meall.setMenu(menu);
        return mealRepo.save(meall);
    }


}
