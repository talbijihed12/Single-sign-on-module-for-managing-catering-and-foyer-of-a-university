package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.config.KeycloakConfig;

import com.example.claimsmicroservice.dto.SmsRequest;
import com.example.claimsmicroservice.entities.Claim;

import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.responses.MessageResponse;
import com.example.claimsmicroservice.services.IServiceClaim;
import com.example.claimsmicroservice.usermcroservices.UserDto;
import com.example.claimsmicroservice.usermcroservices.UserDtoClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ClaimController {
    private IServiceClaim iServiceRec;

    private ModelMapper modelMapper;
    private UserDtoClient userDtoClient;


    //student and teacher
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addClaim")
    public Claim addReclamation(@RequestBody Claim request, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        String email = context.getToken().getEmail();
        Map<String, Object> model = new HashMap<>();
        model.put("email",email);
        model.put("title",request.getTitle());
        model.put("typeReclamation",request.getTypeReclamation());
        model.put("status",request.getStatus());
        model.put("description",request.getDescription());
        model.put("username",username);
        request.setEmail(email);
        request.setUsername(username);
        return iServiceRec.addClaim(request);
    }




    //admin student and teacher
    @GetMapping("/allClaims")
    public List<Claim> getAllReclamations () {
        return iServiceRec.findAllClaims();
    }

    //admin student and teacher
    @GetMapping("/findClaim/{id}")
    public Claim getReclamationById (@PathVariable("id") Long id) {
        return iServiceRec.findClaimById(id);
    }

    //admin
    /*@PutMapping("/changeStatus/{id}")
    public ResponseEntity<ClaimDto> changeStatus(@PathVariable Long id, @RequestBody ClaimDto claimDto) {

        // convert DTO to Entity
        Claim claimRequest = modelMapper.map(claimDto, Claim.class);

        Claim claim = iServiceRec.changeStatus(id, claimRequest);

        // entity to DTO
        ClaimDto claimResponse = modelMapper.map(claim, ClaimDto.class);

        return ResponseEntity.ok().body(claimResponse);
    }*/
    @PutMapping("/changeStatus")
    public Claim changeStatus(@RequestBody Claim claim){
        return iServiceRec.changeStatus(claim);
    }

    //student and teacher

    @PostMapping("/cancelClaim/{id}")
    public void cancelReclamation(@PathVariable Long id) {
        iServiceRec.cancelClaim(id);
    }


    //student and teacher

    @DeleteMapping("/removeClaim/{id}")
    public void removeReclamation(@PathVariable Long id){
        iServiceRec.removeClaim(id);
    }

    //admin

    @GetMapping("/nbClaimsResolu/{d1}/{d2}")
    public Integer nbReclamationsResolu(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDiff, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRes) {
        return iServiceRec.nbClaimsResolu(dateDiff,dateRes);
    }
    //admin
    /*@PatchMapping("/resolveClaim")
    public Claim resolveClaim(@RequestBody Claim claim){
        Map<String, Object> model = new HashMap<>();
        model.put("email",claim.getEmail());
        model.put("username", claim.getUsername());
        model.put("title",claim.getTitle());
        model.put("typeReclamation",claim.getTypeReclamation());
        model.put("status",claim.getStatus());
        model.put("description",claim.getDescription());

        return iServiceRec.resolveClaim(claim);
    }*/
    @PutMapping("/resoudre")
    public Claim resoudre(@RequestBody Claim claim){
        return iServiceRec.resolveClaim(claim);
    }
    //admin
    /*@PatchMapping("/returnClaim/{id}")
    public ResponseEntity<ClaimRetourDto> returnClaim(@PathVariable Long id,@RequestBody ClaimRetourDto claimRetourDto){

        // convert DTO to Entity
        Claim claimRequest = modelMapper.map(claimRetourDto, Claim.class);

        Claim claim = iServiceRec.returnClaim(id,claimRequest);

        // entity to DTO
        ClaimRetourDto claimResponse = modelMapper.map(claim, ClaimRetourDto.class);

        return ResponseEntity.ok().body(claimResponse);
    }*/
    @PutMapping("/retour")
    public Claim returnClaim(@RequestBody Claim claim){
        return iServiceRec.returnClaim(claim);
    }

    //admin
    /*@PatchMapping("/archiveClaim/{id}")
    public Claim archiveClaim(@PathVariable Long id){
        return iServiceRec.archiveClaim(id);
    }*/
    @PutMapping("/archive")
    public Claim archiveClaim(@RequestBody Claim claim){
        return iServiceRec.archiveClaim(claim);
    }

    //student and teacher
    @PutMapping("/modifier")
    public Claim modifier(@RequestBody Claim claim){
        return iServiceRec.editClaim(claim);
    }
    //admin
    @GetMapping("/findAllByStatusNotAnnuler")
    public ResponseEntity<List<Claim>> getAllReclamationsByStatus () {

        List<Claim> reclamations  = iServiceRec.findReclamationByStatus();

        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }
    //admin student and teacher

    @GetMapping("/findByDateDiff/{d1}/{d2}")
    public List<Claim> findByDateDiff(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return iServiceRec.findByDateDiff(startDate,endDate);
    }
    //admin student and teacher

    @GetMapping("/findByDateRes/{d1}/{d2}")
    public List<Claim> findByDateRes(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return iServiceRec.findByDateRes(startDate,endDate);
    }

    //admin student and teacher
    @GetMapping("/searchByKeywords")
    public List<Claim> search(String keywords) {
        return iServiceRec.search(keywords);

    }
    //admin

    @GetMapping("/listUser")
    public String getUsersInfo() {
        return "Accessing from USER-SERVICE ==> " + userDtoClient.listUsers();
    }


    //admin
    @GetMapping("/retrieveUser/{username}")
    public String retrieveUserInfo(@PathVariable("username") String username) {
        return "Username" + userDtoClient.retrieveUserInfo(username);
    }

    //admin student and teacher
    @GetMapping("/getClaimsByUsername/{username}")
    public ResponseEntity<List<Claim>> getClaimsByUsername (@PathVariable String username) {

        List<Claim> reclamations = iServiceRec.findByUsername(username);

        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }


    //admin
    @DeleteMapping("/banUserToAddClaim/{username}")
    public ResponseEntity ban(@PathVariable String username,@RequestBody SmsRequest smsRequest) {
        iServiceRec.banUserToAddClaim(username,smsRequest);
        return ResponseEntity.ok(new MessageResponse(true, "Success", "Username is banned"));
    }
    //admin student and teacher
    @GetMapping("/findClaimsByTypeReclamation/{type}")
    public List<Claim> findClaimsByTypeReclamation(@PathVariable TypeReclamation type) {
        return iServiceRec.findClaimsByTypeReclamation(type);
    }
    //admin
    @DeleteMapping("/deleteClaimResolu")
    public void deleteClaimByStatusResolu() {
        iServiceRec.deleteClaimByStatusResolu();
    }


    @GetMapping("/sendMessageNotif")
    public String Noticate() {
        return iServiceRec.Noticate();
    }

    //admin

    @GetMapping("/countDaysBetweenDateDiffAndNow/{id}")
    public long daysBetween(@PathVariable Long id){
        return iServiceRec.getDaysBetween(id);
    }
    //admin
    @GetMapping("/findClaimsByStatus/{status}")
    public List<Claim> findClaimsByStatus(@PathVariable Status status) {
        return iServiceRec.findClaimsByStatus(status);
    }
    //admin
    @GetMapping("/findHistoric")
    public List<Claim> findHistorique() {
        return iServiceRec.findHistory();
    }

















}
