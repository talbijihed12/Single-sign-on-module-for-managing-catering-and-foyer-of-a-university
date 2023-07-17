package com.example.reservationmicroservice.Controllers;

import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Pdf.PdfService;
import com.example.reservationmicroservice.Repositories.IReservationFoyerRepository;
import com.example.reservationmicroservice.Repositories.ISingleReservationFoyerRepository;
import com.example.reservationmicroservice.Services.IReservationFoyerService;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import com.example.reservationmicroservice.UserMicroService.UserService;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import lombok.AllArgsConstructor;
import lombok.var;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.apache.commons.io.IOUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.xml.transform.TransformerException;
import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/rf")
@AllArgsConstructor
public class ReservationFoyerController {


    //@Autowired
    private IReservationFoyerService reservationFoyerService;
    private IReservationFoyerRepository reservationFoyerRepository;
    private UserService userService;
    private PdfService pdfService;



    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @PostMapping("/addWithUsername")
    public ReservationFoyer addReservationFoyerUsername(@RequestBody ReservationFoyer rf/*KeycloakAuthenticationToken auth*/) {
        //KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        //KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        //String username = context.getToken().getPreferredUsername();
        return reservationFoyerService.addReservationFoyer(rf,rf.getUsername());
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PostMapping("/add/{username}")
    public ReservationFoyer addReservationFoyer(@RequestBody ReservationFoyer rf, @PathVariable("username") String username, KeycloakAuthenticationToken auth) {
        return reservationFoyerService.addReservationFoyer(rf,username);
    }

    @PostMapping("/add")
    public ReservationFoyer addReservationFoyer(@RequestBody ReservationFoyer rf) {
        return reservationFoyerService.addReservationFoyer(rf);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveAllReservationFoyer")
    public List<ReservationFoyer> getReservationFoyer() {
        List<ReservationFoyer> listReservations = reservationFoyerService.retrieveAllReservationFoyer();
        return listReservations;
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveReservationFoyer/{id}")
    public ReservationFoyer retrieveReservationFoyer(@PathVariable("id") Long Id) {
        return reservationFoyerService.retrieveReservationFoyer(Id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @DeleteMapping("/removeReservationFoyer/{id}")
    public void removeReservationFoyer(@PathVariable("id") Long Id) {
        reservationFoyerService.deleteReservationFoyer(Id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/modifyReservationFoyer/{id}")
    public ReservationFoyer modifyReservationFoyer(@RequestBody ReservationFoyer rf, @PathVariable("id")Long id) {
        return reservationFoyerService.updateReservationFoyer(rf,id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/approveReservationFoyer/{id}")
    public void approveReservationFoyer(@PathVariable("id") Long id, @RequestBody List<Long> listIdRoom){
        reservationFoyerService.approveReservationFoyer(id,listIdRoom);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @DeleteMapping("/cancelReservationFoyer/{id}")
    public void cancelReservationFoyer(@PathVariable("id") Long id) {
        reservationFoyerService.cancelReservationFoyer(id);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveReservationFoyerBetweenTwoDates/{dd}/{df}")
    public List<ReservationFoyer> retrieveReservationFoyerBetweenTwoDates(@PathVariable("dd")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dd, @PathVariable("df") @DateTimeFormat(pattern = "yyyy-MM-dd") Date df) {
        return reservationFoyerService.retrieveReservationFoyerBetweenTwoDates(dd,df);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveApprovedReservationFoyerBetweenTwoDates/{dd}/{df}")
    public List<ReservationFoyer> retrieveApprovedReservationFoyerBetweenTwoDates(@PathVariable("dd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dd,@PathVariable("df") @DateTimeFormat(pattern = "yyyy-MM-dd") Date df){
        return reservationFoyerService.retrieveApprovedReservationFoyerBetweenTwoDates(dd,df);
    }
    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/retrieveReservationFoyerByUserId")
    public List<ReservationFoyer> retrieveReservationFoyerByUsername(KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return reservationFoyerService.retrieveReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveReservationFoyerByUserId/{username}")
    public List<ReservationFoyer> retrieveReservationFoyerByUsername(@PathVariable("username") String username){
        return reservationFoyerService.retrieveReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/retrieveActiveReservationFoyer")
    public List<ReservationFoyer> retrieveActiveReservationFoyer(){
        return reservationFoyerService.retrieveActiveReservationFoyer();
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("retrieveActiveReservationFoyerByUsername/{username}")
    public List<ReservationFoyer> retrieveActiveReservationFoyerByUserId(@PathVariable("username") String username){
        return reservationFoyerService.retrieveActiveReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("retrieveActiveReservationFoyerByUsername")
    public List<ReservationFoyer> retrieveActiveReservationFoyerByUserId( KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        String username = context.getToken().getPreferredUsername();
        return reservationFoyerService.retrieveActiveReservationFoyerByUsername(username);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping(value="/getQRCodeWithUsername/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[]  getQRCodeWithUsername(HttpServletResponse response,@PathVariable("id") Long id) throws IOException {
        String path = reservationFoyerService.GenerateQrCodeWithUsername(id);
        InputStream in = new FileInputStream(path);

        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value="/getpdf/{id}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF(@PathVariable("id") Long id) {

        String path = reservationFoyerService.GenerateQrCodeWithUsername(id);
        InputStream in = null;


            try {
                in = new FileInputStream( pdfService.createPdf(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            };


        byte[] contents = new byte[0];
        try {
            contents = new byte[in.available()];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
}

