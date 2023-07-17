package com.example.restorationmicroservice.Repository;

import com.example.restorationmicroservice.Entity.Meal;
import com.example.restorationmicroservice.Entity.Vote;
import com.example.restorationmicroservice.Entity.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepo extends JpaRepository<Vote, Long> {

    Optional<Vote> findVoteByMealAndUsernameOrderByVoteIdDesc(Meal meal, String username);

    List<Vote> findVoteByVoteType(VoteType voteType);
    List<Vote> findVoteByUsername(String username);
    List<Vote> findVoteByUsernameAndMealId(String username,Long id );

    //Vote countVoteByMealId(Meal meal);
    Integer countVoteByVoteType(VoteType voteType);
    List<Vote>  findVoteByMealId (Long id);
    Integer countVoteByMealId(Long id);
}
