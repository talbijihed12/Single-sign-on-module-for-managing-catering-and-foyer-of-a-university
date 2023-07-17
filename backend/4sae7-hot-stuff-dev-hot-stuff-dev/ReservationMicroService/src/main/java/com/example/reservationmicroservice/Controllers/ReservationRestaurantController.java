package com.example.reservationmicroservice.Controllers;

import com.example.reservationmicroservice.Entity.Allergy;
import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.Repositories.IReservationRestaurantRepository;
import com.example.reservationmicroservice.Services.IReservationFoyerService;
import com.example.reservationmicroservice.Services.IReservationRestaurantService;
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
@RequestMapping("/rr")
@AllArgsConstructor
@CrossOrigin("*")
public class ReservationRestaurantController {
    private IReservationRestaurantService reservationRestaurantService;
    private IReservationRestaurantRepository reservationRestaurantRepository;

    @PostMapping("/add")
    public ReservationRestaurant addReservationRestaurant(@RequestBody ReservationRestaurant rr) {
        return reservationRestaurantService.addReservationRestaurant(rr);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addWithUsername")
    public ReservationRestaurant addReservationRestaurant(@RequestBody ReservationRestaurant rr, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        UserDTO user = new UserDTO();
        user.setNom(context.getToken().getFamilyName());
        user.setEmail(context.getToken().getEmail());
        user.setUsername(context.getToken().getPreferredUsername());
        user.setGender(context.getToken().getGender());
        return reservationRestaurantService.addReservationRestaurant(rr,user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveAllReservationRestaurant")
    public List<ReservationRestaurant> getReservationRestaurant() {
        List<ReservationRestaurant> listReservations = reservationRestaurantService.retrieveAllReservationRestaurant();
        return listReservations;
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveReservationRestaurant/{id}")
    public ReservationRestaurant retrieveReservationRestaurant(@PathVariable("id") Long Id) {
        return reservationRestaurantService.retrieveReservationRestaurant(Id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @DeleteMapping("/removeReservationRestaurant/{id}")
    public void removeReservationRestaurant(@PathVariable("id") Long Id) {
        reservationRestaurantService.deleteReservationRestaurant(Id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PutMapping("/modifyReservationRestaurant/{id}")
    public ReservationRestaurant modifyReservationRestaurant(@RequestBody ReservationRestaurant rr, @PathVariable("id")Long id) {
        return reservationRestaurantService.updateReservationRestaurant(rr,id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PutMapping("/approveReservationRestaurant/{id}")
    public void approveReservationRestaurant(@PathVariable("id") Long id) {
        reservationRestaurantService.approveReservationRestaurant(id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveApprovedReservationRestaurantBetweenTwoDates/{dd}/{df}")
    public List<ReservationRestaurant> retrieveApprovedReservationRestaurantBetweenTwoDates(@PathVariable("dd")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dd, @PathVariable("df")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date df){
        return reservationRestaurantService.retrieveReservationRestaurantBetweenTwoDates(dd,df);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveReservationRestaurantByUserName/{username}")
    public List<ReservationRestaurant> retrieveReservationRestaurantByUsername(@PathVariable("username") String username){
        UserDTO user = new UserDTO();
        user.setUsername(username);
        return reservationRestaurantService.retrieveReservationRestaurantByUsername(user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveReservationRestaurantByUserName")
    public List<ReservationRestaurant> retrieveReservationRestaurantByUsername(KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        UserDTO user = new UserDTO();
        user.setNom(context.getToken().getFamilyName());
        user.setEmail(context.getToken().getEmail());
        user.setUsername(context.getToken().getPreferredUsername());
        user.setGender(context.getToken().getGender());
        return reservationRestaurantService.retrieveReservationRestaurantByUsername(user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveActiveReservationRestaurant")
    public List<ReservationRestaurant> retrieveActiveReservationRestaurant(){
        return reservationRestaurantService.retrieveActiveReservationRestaurant();
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveActiveReservationRestaurantByUsername/{username}")
    public List<ReservationRestaurant> retrieveActiveReservationRestaurantByUsername(@PathVariable("username") String username){
        UserDTO user = new UserDTO();
        user.setUsername(username);
        return reservationRestaurantService.retrieveActiveReservationRestaurantByUsername(user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveActiveReservationRestaurantByUsername")
    public List<ReservationRestaurant> retrieveActiveReservationRestaurantByUsername(KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        UserDTO user = new UserDTO();
        user.setNom(context.getToken().getFamilyName());
        user.setEmail(context.getToken().getEmail());
        user.setUsername(context.getToken().getPreferredUsername());
        user.setGender(context.getToken().getGender());
        return reservationRestaurantService.retrieveActiveReservationRestaurantByUsername(user);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping(value="/getQRCodeWithUsername/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[]  getQRCodeWithUsername(@PathVariable("id") Long id) throws IOException {
        String path = reservationRestaurantService.GenerateQrCodeWithUsername(id);
        InputStream in = new FileInputStream(path);
        return IOUtils.toByteArray(in);
    }
    @GetMapping("/retrieveAllReservationRestaurantMS")
    public List<ReservationRestaurant> getReservationRestaurantMS() {
        List<ReservationRestaurant> listReservations = reservationRestaurantService.retrieveAllReservationRestaurant();
        return listReservations;
    }
    @GetMapping("/findReservationRestaurantByAllergiesAndApprovedAndExpired/{allergy}/{approved}/{expired}")
    public List<ReservationRestaurant> findresByAllAndAppAndExp (@PathVariable("allergy")Allergy allergy, @PathVariable("approved") Boolean approved ,@PathVariable("expired") Boolean expired){
        List<ReservationRestaurant> list1 = reservationRestaurantRepository.findReservationRestaurantsByAllergiesContains(allergy);
        List<ReservationRestaurant> list2 = reservationRestaurantRepository.findReservationRestaurantsByExpiredAndApproved(expired,approved);
        list1.retainAll(list2);
        return list1;
    }
}
