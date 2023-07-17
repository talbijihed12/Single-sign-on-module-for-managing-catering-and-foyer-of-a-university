package com.example.reservationmicroservice.Controllers;

import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import com.example.reservationmicroservice.Services.ISingleReservationFoyerService;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/srf")
@AllArgsConstructor
@CrossOrigin("*")
public class SingleReservationFoyerController {
    ISingleReservationFoyerService singleReservationFoyerService;
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PostMapping("/addSingleReservationFoyer/{username}")
    public SingleReservationFoyer addSingleReservationFoyer(@RequestBody SingleReservationFoyer srf,@PathVariable("username") String username){
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setEmail("none");
        return singleReservationFoyerService.addSingleReservationFoyer(srf,user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addSingleReservationFoyer")
    public SingleReservationFoyer addSingleReservationFoyer(@RequestBody SingleReservationFoyer srf,KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        UserDTO user = new UserDTO();
        user.setNom(context.getToken().getFamilyName());
        user.setEmail(context.getToken().getEmail());
        user.setUsername(context.getToken().getPreferredUsername());
        user.setGender(context.getToken().getGender());
        return singleReservationFoyerService.addSingleReservationFoyer(srf,user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @DeleteMapping("/deleteSingleReservationFoyer/{id}")
    public void deleteSingleReservationFoyer(@PathVariable("id") Long id){
        singleReservationFoyerService.deleteSingleReservationFoyer(id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/updateSingleReservationFoyer/{id}")
    public SingleReservationFoyer updateSingleReservationFoyer(@RequestBody SingleReservationFoyer srf,@PathVariable("id")Long id){
        return singleReservationFoyerService.updateSingleReservationFoyer(srf,id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveSingleReservationFoyer/{id}")
    public SingleReservationFoyer retrieveSingleReservationFoyer(@PathVariable("id") Long id){
        return singleReservationFoyerService.retrieveSingleReservationFoyer(id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveAllSingleReservationFoyer")
    public List<SingleReservationFoyer> retrieveAllSingleReservationFoyer(){
        return singleReservationFoyerService.retrieveAllSingleReservationFoyer();
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @DeleteMapping("/cancelSingleReservationFoyer/{id}")
    public void cancelSingleReservationFoyer(@PathVariable("id") Long id){
        singleReservationFoyerService.cancelSingleReservationFoyer(id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveSingleReservationFoyerBetweenTwoDates/{dd}/{df}")
    public List<SingleReservationFoyer> retrieveSingleReservationFoyerBetweenTwoDates(@PathVariable("dd")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dd, @PathVariable("df")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date df){
        return singleReservationFoyerService.retrieveSingleReservationFoyerBetweenTwoDates(dd,df);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveSingleReservationFoyerByUsername")
    public List<SingleReservationFoyer> retrieveSingleReservationFoyerByUsername( KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return singleReservationFoyerService.retrieveSingleReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveSingleReservationFoyerByUsername/{username}")
    public List<SingleReservationFoyer> retrieveSingleReservationFoyerByUsername(@PathVariable("username")  String username){
        return singleReservationFoyerService.retrieveSingleReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveActiveSingleReservationFoyer")
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyer(){
        return singleReservationFoyerService.retrieveActiveSingleReservationFoyer();
    }
    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveActiveSingleReservationFoyerByUsername/{username}")
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyerByUsername(@PathVariable("username") String username){
        return singleReservationFoyerService.retrieveActiveSingleReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveActiveSingleReservationFoyerByUsername")
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyerByUsername( KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return singleReservationFoyerService.retrieveActiveSingleReservationFoyerByUsername(username);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping(value="/getQRCodeWithUsername/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[]  getQRCodeWithUsername(@PathVariable("id") Long id) throws IOException {
        String path = singleReservationFoyerService.GenerateQrCodeWithUsername(id);
        InputStream in = new FileInputStream(path);
        return IOUtils.toByteArray(in);
    }
}
