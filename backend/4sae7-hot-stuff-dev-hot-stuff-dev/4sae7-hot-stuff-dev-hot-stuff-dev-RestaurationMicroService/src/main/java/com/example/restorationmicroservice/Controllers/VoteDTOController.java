package com.example.restorationmicroservice.Controllers;

import com.example.restorationmicroservice.UserMicroservice.UserDto;
import com.example.restorationmicroservice.UserMicroservice.UserServiceDto;
import com.example.restorationmicroservice.DTO.VoteDto;
import com.example.restorationmicroservice.Entity.Vote;
import com.example.restorationmicroservice.Entity.VoteType;
import com.example.restorationmicroservice.Services.VoteServiceImpl;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class VoteDTOController {

    private  VoteServiceImpl voteService;
    private UserServiceDto userServiceDto;

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/Vote")
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDTO, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        voteService.vote(voteDTO,auth);
        return new ResponseEntity<>(HttpStatus.OK);}


    @GetMapping("/findUserByUsername/{username}")
    UserDto findUserByUsername(@PathVariable("username") String username){
        return userServiceDto.findUserByUsername(username);}
    @GetMapping("/listUser")
    public String getUsersInfo() {
        return "Accessing from USER-SERVICE ==> " + userServiceDto.listUsers();}

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveVoteByUserName/{username}")
    public List<Vote> retrieveVoteByUserName(@PathVariable("username")String username) {
        return voteService.retrieveVoteByUserName(username);}

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveVoteByUsernameAndMeal/{username}/{idmeal}")
    public List<Vote> retrieveVoteByUsernameAndMeal(@PathVariable("username")String username,@PathVariable("idmeal")Long id ){
        return voteService.retrieveVoteByUsernameAndMeal(username,id);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/countVoteByMeal/{idmeal}")
    Integer countVoteByMeal(@PathVariable("idmeal") Long id){
        return voteService.countVoteByMealId(id);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/changeVoteType/{voteId}")
    public Vote changeVoteType(@PathVariable Long voteId,@RequestBody Vote votee) {
       return voteService.changeVoteType(voteId,votee);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/nbrofvote/{votetype}")
    public Integer nbrofvote(@PathVariable VoteType votetype){
        return voteService.nbrvotebyVotetype(votetype);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping ("/removeVote/{voteId}")
    public void removeVote(@PathVariable Long voteId){
        voteService.removeVote(voteId);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findVoteByMealId/{id}")
    public List<Vote> findVoteByMealId(@PathVariable  Long id) {
        return voteService.findVoteByMealId(id);}

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/getallvotes")
    List<Vote> getallvotes(){
        return voteService.getallvotes();
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/desaffecterVotee/{voteId}")
    public void desaffecterVotee (@PathVariable("voteId") Long voteId){
         voteService.desaffecterVotee(voteId);}
}
