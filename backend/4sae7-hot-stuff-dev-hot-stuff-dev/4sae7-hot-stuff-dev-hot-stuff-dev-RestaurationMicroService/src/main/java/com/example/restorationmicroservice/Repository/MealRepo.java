package com.example.restorationmicroservice.Repository;


import com.example.restorationmicroservice.Entity.Meal;
import com.example.restorationmicroservice.Entity.TypeMenu;
import com.example.restorationmicroservice.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MealRepo extends JpaRepository<Meal,Long> {
    List<Meal> findMealByDatestart(Date datestart);
    Integer countMealsByVoteCount(Integer voteCount);
    Meal findMealByMenuTypeMenu(TypeMenu typeMenu);
}

