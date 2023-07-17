package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.DTO.VoteDto;
import com.example.restorationmicroservice.EmailService.IEmailService;
import com.example.restorationmicroservice.Entity.Meal;
import com.example.restorationmicroservice.Entity.Vote;
import com.example.restorationmicroservice.Entity.VoteType;
import com.example.restorationmicroservice.Exceptions.MealNotFoundException;
import com.example.restorationmicroservice.Exceptions.SpringRedditException;
import com.example.restorationmicroservice.Exceptions.VoteNotFoundException;
import com.example.restorationmicroservice.Repository.MealRepo;
import com.example.restorationmicroservice.Repository.VoteRepo;
import com.example.restorationmicroservice.UserMicroservice.UserDto;
import com.example.restorationmicroservice.UserMicroservice.UserServiceDto;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static com.example.restorationmicroservice.Entity.VoteType.DOWNVOTE;
import static com.example.restorationmicroservice.Entity.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteServiceImpl {
    private VoteRepo voteRepo;
    private MealRepo mealRepo;

    private UserServiceDto userServiceDto;
    private IEmailService iEmailService;

    public List<Vote> getallvotes() {
        return voteRepo.findAll();
    }

    @Transactional
    public void vote(VoteDto voteDto, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        Meal meal = mealRepo.findById(voteDto.getId())
                .orElseThrow(() -> new MealNotFoundException("Meal Not Found with ID - " + voteDto.getId()));
       Optional<Vote> voteByMealAndUser = voteRepo.findVoteByMealAndUsernameOrderByVoteIdDesc(meal, username);
        if (voteByMealAndUser.isPresent() && voteByMealAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "+ voteDto.getVoteType() + "'d  this meal");}
        if (UPVOTE.equals(voteDto.getVoteType())) {
            meal.setVoteCount(meal.getVoteCount() + 1);
            voteDto.setUsername(username);
        } else {
            meal.setVoteCount(meal.getVoteCount() - 1);
            voteDto.setUsername(username);
        }
        voteRepo.save(mapToVote(voteDto, meal));
        mealRepo.save(meal);

        //UserDto userDto = userServiceDto.findUserByUsername(voteDto.getUsername());
       // String to= userDto.getEmail();
        //String subject = "VOTE MEAL !";
        //String text = "Dear, " + userDto.getNom() +" "+ userDto.getPrenom()+  " !\n\n"
          //      + "We are pleased to inform you that your vote has been confirmed . "+"\n\n"
            //    +"Also, if you want to change your opinion is up to you, So do not hesitate ."+"\n\n"
              //  +""
                //+"Sincerely,\n\n"
                //+ "Restauration's Team";
       // iEmailService.sendSimpleMessage(to, subject, text);

    }

    private Vote mapToVote(VoteDto voteDto, Meal meal) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .meal(meal)
                .username(voteDto.getUsername())
                .build();
    }
    public List<Vote> retrieveVoteByUserName(String username) {
        return voteRepo.findVoteByUsername(username);
    }
    public List<Vote> retrieveVoteByUsernameAndMeal(String username,Long id ){
        return voteRepo.findVoteByUsernameAndMealId(username,id);
    }

    public Vote changeVoteType(Long voteId,Vote votee) {
        Vote vote = voteRepo.findById(voteId).orElseThrow(() -> new VoteNotFoundException("Vote Not Found with ID - " + votee.getVoteId()));
        vote.setVoteType(votee.getVoteType());
        return voteRepo.save(vote);
    }
    public Integer nbrvotebyVotetype(VoteType voteType) {
        return voteRepo.countVoteByVoteType(voteType);
    }
    public void removeVote(Long voteId) {
        Vote vote = voteRepo.findById(voteId).orElseThrow(() -> new VoteNotFoundException("Vote Not Found with ID - " ));
        if (vote.getVoteType().equals(UPVOTE)){
            voteRepo.deleteById(voteId);
        } else if (vote.getVoteType().equals(DOWNVOTE)) {
            voteRepo.deleteById(voteId);
        }
    }
    public Integer countVoteByMealId(Long id){
        return voteRepo.countVoteByMealId(id);
    }
    public List<Vote> findVoteByMealId(Long id) {

        return voteRepo.findVoteByMealId(id);
    }

    public void desaffecterVotee (Long voteId) {
        Vote vote = voteRepo.findById(voteId).orElseThrow(() -> new VoteNotFoundException("Vote Not Found with ID - " ));
        if(vote.getVoteType().equals(UPVOTE))
        {
            vote.setVoteType(null);
            voteRepo.save(vote);
        }
        else
        {
            vote.getVoteType().equals(DOWNVOTE);
            vote.setVoteType(null);
            voteRepo.save(vote);
        }
    }
    @Scheduled(cron = "*/60 * * * * *")
    public void desaffecterVote() {
        List<Vote> votes = voteRepo.findVoteByVoteType(UPVOTE);
        for (Vote  z: votes){
            z.setVoteType(null);
        }}


    }

