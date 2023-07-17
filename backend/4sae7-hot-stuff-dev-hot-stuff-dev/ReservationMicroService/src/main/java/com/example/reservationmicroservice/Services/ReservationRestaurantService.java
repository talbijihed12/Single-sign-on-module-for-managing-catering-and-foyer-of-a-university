package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.EmailService.EmailService;
import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import com.example.reservationmicroservice.QRCode.QRCodeGenerator;
import com.example.reservationmicroservice.Repositories.IReservationRestaurantRepository;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import com.example.reservationmicroservice.UserMicroService.UserService;
import com.example.reservationmicroservice.config.KeycloakConfig;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationRestaurantService implements IReservationRestaurantService {
    IReservationRestaurantRepository reservationRestaurantRepository;
    private UserService userService;
    private EmailService emailService;
    @Override
    public ReservationRestaurant addReservationRestaurant(ReservationRestaurant rr) {
        return reservationRestaurantRepository.save(rr);
    }
    @Override
    public ReservationRestaurant addReservationRestaurant(ReservationRestaurant rr,UserDTO user) {
        rr.setUsername(user.getUsername());
        return reservationRestaurantRepository.save(rr);
    }

    @Override
    public void deleteReservationRestaurant(Long id) {
        ReservationRestaurant rr = reservationRestaurantRepository.findById(id).get();
        if (rr.getUsername() != null) {
            UserDTO userDTO = userService.retrieveUserInfo(rr.getUsername());
            String subject = "Academic-Home Reservation cancelled !";
            String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                    + rr.getDateStart().toString()
                    + " To :"
                    + rr.getDateEnd().toString()
                    + " has been cancelled !"
                    + "\n\n"
                    + "We hope to see you soon !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);
        }
        reservationRestaurantRepository.deleteById(id);
    }

    @Override
    public ReservationRestaurant updateReservationRestaurant(ReservationRestaurant rr, Long id) {
        rr.setIdR(id);
        reservationRestaurantRepository.save(rr);
        if (rr.getUsername() != null) {
            //UserDTO userDTO= userService.retrieveUserInfo(rr.getUsername());
            UserDTO userDTO = new UserDTO();
            String subject = "Academic-Home Reservation updated !";
            String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                    + rr.getDateStart().toString()
                    + " To :"
                    + rr.getDateEnd().toString()
                    + " has been updated !"
                    + "\n\n"
                    + "We are excited to see you guys !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
           // emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);
        }
        return rr;
    }

    @Override
    public ReservationRestaurant retrieveReservationRestaurant(Long id) {
        return reservationRestaurantRepository.findById(id).get();
    }

    @Override
    public List<ReservationRestaurant> retrieveAllReservationRestaurant() {
        return reservationRestaurantRepository.findAll();
    }

    @Override
    public void approveReservationRestaurant(Long id) {
        ReservationRestaurant rr = retrieveReservationRestaurant(id);
        rr.setApproved(true);
        updateReservationRestaurant(rr,id);
        if (rr.getUsername() != null) {
            UserDTO userDTO = new UserDTO();
            List<UserRepresentation> aa = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users()
                    .search(rr.getUsername());
            for (UserRepresentation a:aa) {
                userDTO.setEmail(a.getEmail());
                userDTO.setPrenom(a.getFirstName());
                userDTO.setNom(a.getLastName());
            }
            //UserDTO userDTO = userService.retrieveUserInfo(rr.getUsername());
            String subject = "Academic-Home Reservation approved !";
            String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                    + rr.getDateStart().toString()
                    + " To :"
                    + rr.getDateEnd().toString()
                    + " has been approved !"
                    + "\n\n"
                    + "We are excited to see you guys !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);
        }
    }

    @Override
    public List<ReservationRestaurant> retrieveReservationRestaurantBetweenTwoDates(Date dd, Date df) {
        List<ReservationRestaurant> list1 = reservationRestaurantRepository.findReservationRestaurantsByDateStartBetween(dd,df);
        List<ReservationRestaurant> list2 = reservationRestaurantRepository.findReservationRestaurantByDateEndBetween(dd,df);
        for (ReservationRestaurant x : list1){
            if (!list2.contains(x))
                list2.add(x);
        }
        return list2;
    }

    @Override
    public List<ReservationRestaurant> retrieveApprovedReservationRestaurantBetweenTwoDates(Date dd, Date df) {
        List<ReservationRestaurant> list1 = retrieveReservationRestaurantBetweenTwoDates(dd,df);
        List<ReservationRestaurant> list2 = reservationRestaurantRepository.findReservationRestaurantsByApproved(true);
        list1.retainAll(list2);
        return list1;
    }

    @Override
    public List<ReservationRestaurant> retrieveReservationRestaurantByUsername(UserDTO user) {
        return reservationRestaurantRepository.findReservationRestaurantsByUsername(user.getUsername());
    }

    @Override
    public List<ReservationRestaurant> retrieveActiveReservationRestaurant() {
        return reservationRestaurantRepository.findReservationRestaurantsByExpired(false);
    }

    @Override
    public List<ReservationRestaurant> retrieveActiveReservationRestaurantByUsername(UserDTO user) {
        return reservationRestaurantRepository.findReservationRestaurantsByExpiredAndUsername(false,user.getUsername());
    }

    @Override
    public String GenerateQrCodeWithUsername (Long id) {
        final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/"+"rr"+id.toString()+".png";

        ReservationRestaurant rr=reservationRestaurantRepository.findById(id).get();


        String text = rr.getUsername()
                + "\n"
                + rr.getNameGrp()
                +"\n"
                +rr.getDateStart().toString()
                +"\n"
                +rr.getDateEnd().toString()
                +"\n"
                +rr.getNbrPerson().toString()
                +"\n"
                +rr.getPension()
                + "\n\n";
        try {
            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(text,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return QR_CODE_IMAGE_PATH;
    }
}
