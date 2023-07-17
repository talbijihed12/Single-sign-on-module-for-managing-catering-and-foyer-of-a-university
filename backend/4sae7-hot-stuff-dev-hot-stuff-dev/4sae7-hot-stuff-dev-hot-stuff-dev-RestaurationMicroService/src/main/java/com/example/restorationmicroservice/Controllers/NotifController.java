package com.example.restorationmicroservice.Controllers;

import com.example.restorationmicroservice.Reservationmicroservice.ReservationRestaurantDTO;
import com.example.restorationmicroservice.Services.NotifService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class NotifController {

    private NotifService notifService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PostMapping("/EnvoyerNotifStandard/{username}")
    public void EnvoyerNotifStandard(@RequestBody ReservationRestaurantDTO reservationRestaurantDTO,@PathVariable("username") String username){
        notifService.EnvoyerNotifStandard(reservationRestaurantDTO,username);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PostMapping("/EnvoyerNotifPersonnalise/{username}")
    public void EnvoyerNotifPersonnalise(@RequestBody ReservationRestaurantDTO reservationRestaurantDTO,@PathVariable("username") String username){
        notifService.EnvoyerNotifPersonnalise(reservationRestaurantDTO,username);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/getnmbr")
    public long getDaysBetween(Long id){
       return notifService.getDaysBetween(id);
    }
}
