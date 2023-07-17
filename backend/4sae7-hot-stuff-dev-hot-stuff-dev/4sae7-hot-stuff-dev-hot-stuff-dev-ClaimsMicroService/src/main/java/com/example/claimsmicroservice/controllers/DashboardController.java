package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.responses.BarResponse;
import com.example.claimsmicroservice.responses.PieResponse;
import com.example.claimsmicroservice.services.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private DashboardService dashboardService;

    //admin
    /*@GetMapping("/nbClaimByStatus")
    public PieResponse pieClaim() {
        return  dashboardService.pieClaim();
    }*/
    @GetMapping("/claim/admin")
    public PieResponse pieReclamation() {
        return  dashboardService.pieReclamation();
    }
    //admin

    /*@GetMapping("/nbClaimByMonth")
    public BarResponse getClaims() {
        return  dashboardService.getClaims();}*/
    @GetMapping("/claims")
    public BarResponse getClaims() {
        return  dashboardService.getClaims();
    }

    //admin

    @GetMapping("/nbClaimByStatusAndUsername/{username}")
    public PieResponse getNbClaimByStatusAndUsername(@PathVariable String username) {
        return  dashboardService.pieClaimByStatusAndUsername(username);
    }
    //admin
    @GetMapping("/getNbClaimsByType")
    public PieResponse getNbClaimsByType() {
        return  dashboardService.getClaimsByType();
    }


}