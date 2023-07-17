package com.example.subscriptionmicroservice.Controller;
import com.example.subscriptionmicroservice.Entity.*;
import com.example.subscriptionmicroservice.Repository.Abonnement_F_Repository;
import com.example.subscriptionmicroservice.Repository.Abonnement_R_Repository;
import com.example.subscriptionmicroservice.Service.IAbonnementService;
import com.example.subscriptionmicroservice.Service.MicroSU;
import lombok.AllArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/abonn")
@CrossOrigin("*")
public class AbonnementController {
    IAbonnementService abonnementService;
    MicroSU microSU;
    @Autowired
    private Abonnement_F_Repository abonnement_f_repository;
    @Autowired
    private Abonnement_R_Repository abonnement_r_repository;

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PostMapping("/addAbonnementF/{username}")
    int addAbonnementF(@RequestBody Abonnement_F abonnement_f, @PathVariable("username") String username) {
        return abonnementService.addAbonnementF(abonnement_f, username);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addAbonnementF")
    int addAbonnementF(@RequestBody Abonnement_F abonnement_f, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return abonnementService.addAbonnementF(abonnement_f, username);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/modifyAbonnementF")
    Abonnement_F modifyAbonnementF(@RequestBody Abonnement_F abonnement_f) {
        return abonnementService.modifyAbonnementF(abonnement_f);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @DeleteMapping("/deleteAbonnementF/{id}")
    void deleteAbonnementF(@PathVariable long id) {
        abonnementService.deleteAbonnementF(id);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addAbonnementR")
    int addAbonnementR(@RequestBody Abonnement_R abonnement_r, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return abonnementService.addAbonnementR(abonnement_r, username);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PutMapping("/modifyAbonnementR/{id}")
    Abonnement_R modifyAbonnementR(@RequestBody Abonnement_R abonnement_r, @PathVariable long id) {
        return abonnementService.modifyAbonnementR(abonnement_r, id);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @DeleteMapping("/deleteAbonnementR/{id}")
    void deleteAbonnementR(@PathVariable long id) {
        abonnementService.deleteAbonnementR(id);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/allF")
    List<Abonnement_F> retrieveAllF() {
        return abonnementService.retrieveAllF();
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PutMapping("/addJeton/{nbJeton}/{username}")
    int addJeton(KeycloakAuthenticationToken auth, @PathVariable ("nbJeton") int nbJeton, @PathVariable ("username") String username) {
 //       KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
   //     KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
     //   String username = context.getToken().getPreferredUsername();
        return abonnementService.addJeton(username, nbJeton);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PutMapping("/useJeton/{nbJeton}")
    boolean useJeton(@PathVariable("username") String username, @PathVariable("nbJeton") int nbJeton) {
        //KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        //KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        //String username = context.getToken().getPreferredUsername();
        return abonnementService.useJeton(username, nbJeton);
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/count-by-type/{Type}")
    public Long countByColor(@PathVariable("Type") TypeFoyer tf) {
        return abonnementService.countByColor(tf);
    }

    @GetMapping("/test")
    public List<User> test() {
        return microSU.readUsers();
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/allR")
    List<Abonnement_R> retrieveAllR() {
        return abonnementService.retrieveAllR();

    }

    //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/subscriptionFPercentage")
    public Map<String, Double> getSubscriptionFPercentage() {
        List<Abonnement_F> subscriptions = abonnement_f_repository.findAll();
        Map<String, Double> percentages = new HashMap<>();
        int total = subscriptions.size();
        int basicCount = 0, premiumCount = 0, ultimateCount = 0;
        for (Abonnement_F abonnement_f : subscriptions) {
            if (abonnement_f.getTypeFoyer() == TypeFoyer.ANNUAL) {
                basicCount++;
            } else if (abonnement_f.getTypeFoyer() == TypeFoyer.SEMESTER) {
                premiumCount++;
            } else if (abonnement_f.getTypeFoyer() == TypeFoyer.TRIMESTER) {
                ultimateCount++;
            }
        }
        percentages.put("ANNUAL", (double) basicCount / total * 100);
        percentages.put("SEMESTER", (double) premiumCount / total * 100);
        percentages.put("TRIMESTER", (double) ultimateCount / total * 100);
        return percentages;
    }

    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/subscriptionRPercentage")
    public Map<String, Double> getSubscriptionRPercentage() {
        List<Abonnement_R> subscriptions = abonnement_r_repository.findAll();
        Map<String, Double> percentages = new HashMap<>();
        int total = subscriptions.size();
        int basicCount = 0, premiumCount = 0, ultimateCount = 0;
        for (Abonnement_R abonnement_r : subscriptions) {
            if (abonnement_r.getTypeRestaurant() == TypeRestaurant.ANNUAL) {
                basicCount++;
            } else if (abonnement_r.getTypeRestaurant() == TypeRestaurant.MONTHLY) {
                premiumCount++;
            } else if (abonnement_r.getTypeRestaurant() == TypeRestaurant.Weekly) {
                ultimateCount++;
            }
        }
        percentages.put("ANNUAL", (double) basicCount / total * 100);
        percentages.put("MONTHLY", (double) premiumCount / total * 100);
        percentages.put("Weekly", (double) ultimateCount / total * 100);
        return percentages;
    }

    @GetMapping(value = "/getQRCodeWithUsername/{username}/{nbJeton}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getQRCodeWithUsername(@PathVariable("username") String username, @PathVariable("nbJeton") int nbJeton) throws IOException {
        if (useJeton(username, nbJeton)) {
            InputStream in = new FileInputStream("./src/main/resources/QRCodes/QRCode.png");
            return IOUtils.toByteArray(in);}
        else return null;
    }


}


