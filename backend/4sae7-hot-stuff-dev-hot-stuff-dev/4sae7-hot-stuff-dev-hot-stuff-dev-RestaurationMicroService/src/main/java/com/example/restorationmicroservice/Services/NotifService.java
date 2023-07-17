package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.EmailService.EmailService;
import com.example.restorationmicroservice.EmailService.IEmailService;
import com.example.restorationmicroservice.Entity.Meal;
import com.example.restorationmicroservice.Entity.TypeMenu;
import com.example.restorationmicroservice.Repository.MealRepo;
import com.example.restorationmicroservice.Reservationmicroservice.Allergy;
import com.example.restorationmicroservice.Reservationmicroservice.IReservationRestau;
import com.example.restorationmicroservice.Reservationmicroservice.ReservationRestaurantDTO;
import com.example.restorationmicroservice.UserMicroservice.UserDto;
import com.example.restorationmicroservice.UserMicroservice.UserServiceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class NotifService {
    private UserServiceDto userServiceDto;
    private IEmailService iEmailService;
    private MealRepo mealRepo;
    private IReservationRestau iReservationRestau;
    public void EnvoyerNotifStandard(ReservationRestaurantDTO reservationRestaurantDTO,String username){
        LocalDate now =LocalDate.now();
        List<ReservationRestaurantDTO> reservationRestaurantDTOS= iReservationRestau.findReservationRestaurantByAllergiesAndApprovedAndExpired(Allergy.Oeuf,true,true);
        for (ReservationRestaurantDTO r:reservationRestaurantDTOS)
        {
            // LocalDate todayKolkata = LocalDate.now(ZoneId.from());
            //LocalDate d = r.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //Long no = ChronoUnit.DAYS.between(now,d);
            //if(mealRepo.findMealByMenuTypeMenu(TypeMenu.Standard)){
            if(getDaysBetween(r.getIdR()) > 1){
                System.out.println("hhhhh");
                Meal meal =mealRepo.findMealByMenuTypeMenu(TypeMenu.Standard);
                UserDto userDto = userServiceDto.findUserByUsername(username);
                String to= userDto.getEmail();;
                String subject = "Reservation confirmed !";
                String text = "Dear, " + userDto.getNom() +" "+ userDto.getPrenom()+  " !\n\n"
                        + "We are pleased to inform you that your reservation" +" "+reservationRestaurantDTO.getIdR()
                        +" "+"has been confirmed"+" "+ "for this meal"
                        +" "+meal.getName()+" which is considered as a "+meal.getMenu().getTypeMenu()+" "
                        +" "+"from "+reservationRestaurantDTO.getDateStart()+" "+"to "+reservationRestaurantDTO.getDateEnd() +"."+"\n\n"
                        +"Also, if you want to add or change allergy is up to you, So do not hesitate ."+"\n\n"
                        +""
                        +"Sincerely,\n\n"
                        + "Restauration's Team";
                iEmailService.sendSimpleMessage(to, subject, text);
                System.out.println("heheh");

            }
        }

    }

    public void EnvoyerNotifPersonnalise(ReservationRestaurantDTO reservationRestaurantDTO,String username){
        LocalDate now =LocalDate.now();
        List<ReservationRestaurantDTO> reservationRestaurantDTOS= iReservationRestau.findReservationRestaurantByAllergiesAndApprovedAndExpired(Allergy.Oeuf,true,true);
        for (ReservationRestaurantDTO r:reservationRestaurantDTOS)
        {
            //if(r.getDateStart().toLocalDate().isEqual(now)){
            if(getDaysBetween(r.getIdR()) > 1){
                System.out.println("hhhhh");
                Meal meal =mealRepo.findMealByMenuTypeMenu(TypeMenu.Personnalisé);
                UserDto userDto = userServiceDto.findUserByUsername(username);
                String to= userDto.getEmail();;
                String subject = "Reservation confirmed !";
                String text = "Dear, " + userDto.getNom() +" "+ userDto.getPrenom()+  " !\n\n"
                        + "We are pleased to inform you that your reservation" +" "+reservationRestaurantDTO.getIdR()
                        +" "+"has been confirmed"+" "+ "for this meal"
                        +" "+meal.getName()+" which is considered as a "+meal.getMenu().getTypeMenu()+" "
                        +"with this allergie :" +""+reservationRestaurantDTO.getAllergies()
                        +" "+"from "+reservationRestaurantDTO.getDateStart()+" "+"to "+reservationRestaurantDTO.getDateEnd() +"."+"\n\n"
                        +"Also, if you want to add or change allergy is up to you, So do not hesitate ."+"\n\n"
                        +""
                        +"Sincerely,\n\n"
                        + "Restauration's Team";
                iEmailService.sendSimpleMessage(to, subject, text);
                System.out.println("heheh");
            }
        }

    }
    public long getDaysBetween(Long id) {
        ReservationRestaurantDTO reservationRestaurantDTO = iReservationRestau.retrieveReservationRestaurant(id);
        long daysDiff = reservationRestaurantDTO.getDateEnd().getTime()- reservationRestaurantDTO.getDateStart().getTime() ;//difference between two dates
        long diffInDays = TimeUnit.DAYS.convert(daysDiff, TimeUnit.MILLISECONDS);//convert result in days
        return diffInDays;
    }






}
