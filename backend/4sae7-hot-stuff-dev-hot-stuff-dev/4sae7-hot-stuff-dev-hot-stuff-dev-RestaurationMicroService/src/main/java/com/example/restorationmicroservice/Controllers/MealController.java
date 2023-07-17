package com.example.restorationmicroservice.Controllers;



import com.example.restorationmicroservice.DTO.VoteDto;
import com.example.restorationmicroservice.Entity.Menu;
import com.example.restorationmicroservice.Reservationmicroservice.Allergy;
import com.example.restorationmicroservice.Reservationmicroservice.IReservationRestau;
import com.example.restorationmicroservice.Entity.Meal;
import com.example.restorationmicroservice.Entity.TypeEmplacement;
import com.example.restorationmicroservice.Entity.TypeMenu;
import com.example.restorationmicroservice.Reservationmicroservice.ReservationRestaurantDTO;
import com.example.restorationmicroservice.EmailService.EmailService;
import com.example.restorationmicroservice.Services.IMealService;
import com.example.restorationmicroservice.Services.IService;
import com.example.restorationmicroservice.Services.PdfGenerator;
import com.example.restorationmicroservice.UserMicroservice.UserDto;
import com.example.restorationmicroservice.UserMicroservice.UserServiceDto;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class MealController {
    private IService<Meal> iService;
    private IMealService iMealService;
    private EmailService emailService;
    private IReservationRestau iReservationRestau;
    private UserServiceDto userServiceDto;
  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/getMeal")
    public List<Meal> Read()
    {
        return iService.Read();
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU','PERSONNEL','PROFESSEUR','ETUDIANT')")
    @GetMapping("/getOnemeal/{idmeal}")
    public Meal getOnemeal(@PathVariable("idmeal") Long ID)
    {
        return iService.getOne(ID);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PostMapping("/addmeal")
    public Meal addmeal(@RequestBody Meal meal)
    {
        return iService.Create(meal);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PutMapping("/updateMeal/{idmeal}")
    public Meal updateMeal(@PathVariable("idmeal") Long ID, @RequestBody Meal meal){
        return iService.Update(ID,meal);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @DeleteMapping("/deletemeal/{ID}")
    public void deletemeal(@PathVariable Long ID)
    {
         iService.Delete(ID);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @PostMapping("/affectMealToMenu/{idMeal}/{typeMenu}/{typeEmplacement}")
    public Meal affectMealToMenu(@PathVariable("idMeal") Long idMeal, @PathVariable("typeMenu") TypeMenu typeMenu, @PathVariable("typeEmplacement") TypeEmplacement typeEmplacement) {
        return iMealService.affectMealToMenu(idMeal,typeMenu,typeEmplacement);
    }

  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findmealByDate/{datestart}")
    public List<Meal> getmealByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date datestart){
        return iMealService.getmealByDate(datestart);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/nbMealParVote/{vc}")
    @ResponseBody
    public Integer nbMealParVote(@PathVariable("vc") Integer voteCount){
        return iMealService.nbVoteodMeal(voteCount);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/retrieveAllReservationRestaurant")
    public String getReservationRestaurant() {
        return "Accessing from ReservationRestaurant-SERVICE ==> " + iReservationRestau.getReservationRestaurant();
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response,String username) throws DocumentException, IOException, MessagingException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH_MM_SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=meal" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List<Meal> mealList = iService.Read();
        PdfGenerator generator = new PdfGenerator();
        generator.generateToMeal(mealList, response);
        UserDto userDto = userServiceDto.findUserByUsername(username);
        String to= userDto.getEmail();
        String text = "Dear, " + userDto.getNom() +" "+ userDto.getPrenom()+  " !\n\n"
                + "We are pleased to send you our list of meals . "+"\n\n"
                +""
                +"Sincerely,\n\n"
                + "Restauration's Team";

        emailService.sendMessageWithAttachmentMeal(to, "list of meals !",
                text, "C:\\Users\\MSI\\Downloads\\Documents\\".concat(headervalue.substring(21)));
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findReservationRestaurantByAllergiesAndApprovedAndExpired/{allergy}/{approved}/{expired}")
    public List<ReservationRestaurantDTO> findReservationRestaurantByAllergiesAndApprovedAndExpired(@PathVariable("allergy") Allergy allergy, @PathVariable("approved")Boolean approved, @PathVariable("expired") Boolean expired){
        return iReservationRestau.findReservationRestaurantByAllergiesAndApprovedAndExpired(allergy,approved,expired);
    }

    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findMealByMenuTypeMenu/{typeMenu}")
    public Meal findMealByMenuTypeMenu(@PathVariable("typeMenu") TypeMenu typeMenu) {
        return iMealService.findMealByMenuTypeMenu(typeMenu);
    }

   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_RESTEAU')")
    @GetMapping("/findMealByMenuTypeMenu2/{typeMenu}")
    public Meal findMealByMenuTypeMenu2(@PathVariable("typeMenu") TypeMenu typeMenu) {
        return iMealService.findMealByMenuTypeMenu2(typeMenu);
    }

    @PostMapping("/affectMealToMenuu/{idMeal}")
    public Meal affectMealToMenu(@PathVariable Long idMeal, @RequestBody Menu menu) {
        return iMealService.affectMealToMenuu(idMeal, menu);
    }

    }



