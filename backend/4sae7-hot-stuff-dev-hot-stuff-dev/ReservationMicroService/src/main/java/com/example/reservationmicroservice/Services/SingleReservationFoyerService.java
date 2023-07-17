package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.EmailService.EmailService;
import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.ReservationRestaurant;
import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import com.example.reservationmicroservice.QRCode.QRCodeGenerator;
import com.example.reservationmicroservice.Repositories.ISingleReservationFoyerRepository;
import com.example.reservationmicroservice.RoomMicroService.RoomService;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import com.example.reservationmicroservice.UserMicroService.UserService;
import com.example.reservationmicroservice.config.KeycloakConfig;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Service
@AllArgsConstructor

public class SingleReservationFoyerService implements ISingleReservationFoyerService{
    static Keycloak keycloack;
    RoomService roomService;
    UserService userService;
    EmailService emailService;
    ISingleReservationFoyerRepository singleReservationFoyerRepository;
    @Override
    public SingleReservationFoyer addSingleReservationFoyer(SingleReservationFoyer srf, UserDTO user) {
        srf.setUsername(user.getUsername());
        roomService.BookRoom(srf.getIdRoom());
        singleReservationFoyerRepository.save(srf);

        //UserDTO userDTO = userService.retrieveUserInfo(srf.getUsername());
        String subject = "Academic-Home Reservation confirmed !";
        String text = "Hello, " + user.getNom() +" "+ user.getPrenom()+ "!\n\n"
                + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                + srf.getDateStart().toString()
                + " To :"
                + srf.getDateEnd().toString()
                + " has been confirmed !"
                + "\n\n"
                + "We are excited to see you!\n\n"
                + "Best regards,\n"
                + "The Hot-stuff-Dev Team";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);

        return srf;
    }

    @Override
    public void deleteSingleReservationFoyer(Long id) {
        SingleReservationFoyer srf = singleReservationFoyerRepository.findById(id).get();
        singleReservationFoyerRepository.delete(srf);
    }

    @Override
    public SingleReservationFoyer updateSingleReservationFoyer(SingleReservationFoyer srf, Long id) {
        srf.setId(id);
        return singleReservationFoyerRepository.save(srf);
    }

    @Override
    public SingleReservationFoyer retrieveSingleReservationFoyer(Long id) {
        return singleReservationFoyerRepository.findById(id).get();
    }

    @Override
    public List<SingleReservationFoyer> retrieveAllSingleReservationFoyer() {
        List<SingleReservationFoyer> listSingleReservationFoyer ;
        listSingleReservationFoyer=singleReservationFoyerRepository.findAll();
        for (SingleReservationFoyer srf : listSingleReservationFoyer) {
            if (srf.getDateEnd().before(new Date(currentTimeMillis()))) {
                srf.setExpired(true);
                singleReservationFoyerRepository.save(srf);
            }
        }
        return listSingleReservationFoyer;
    }

    @Override
    public void cancelSingleReservationFoyer(Long id) {
        SingleReservationFoyer srf = retrieveSingleReservationFoyer(id);
        roomService.UnbookRoom(srf.getIdRoom());
        deleteSingleReservationFoyer(id);
        UserDTO userDTO = new UserDTO();
        List<UserRepresentation> aa = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users()
                .search(srf.getUsername());
        for (UserRepresentation a:aa) {
            userDTO.setEmail(a.getEmail());
            userDTO.setPrenom(a.getFirstName());
            userDTO.setNom(a.getLastName());
        }
        //UserDTO userDTO = userService.retrieveUserInfo(srf.getUsername());

        String subject = "Academic-Home Reservation cancelled !";
        String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                + srf.getDateStart().toString()
                + " To :"
                + srf.getDateEnd().toString()
                + " has been cancelled !"
                + "\n\n"
                + "We hope to se you soon!\n\n"
                + "Best regards,\n"
                + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);

    }

    @Override
    public List<SingleReservationFoyer> retrieveSingleReservationFoyerBetweenTwoDates(Date dd, Date df) {
        List<SingleReservationFoyer> list1 = singleReservationFoyerRepository.findSingleReservationFoyersByDateStartBetween(dd,df);
        List<SingleReservationFoyer> list2 = singleReservationFoyerRepository.findSingleReservationFoyerByDateEndBetween(dd,df);
        for (SingleReservationFoyer x : list1){
            if (!list2.contains(x))
                list2.add(x);
        }
        //list1.retainAll(list2);
        return list2;
    }

    @Override
    public List<SingleReservationFoyer> retrieveSingleReservationFoyerByUsername(String username) {
        return singleReservationFoyerRepository.findSingleReservationFoyersByUsername(username);
    }

    @Override
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyer() {
        return singleReservationFoyerRepository.findSingleReservationFoyersByExpired(false);
    }

    @Override
    public List<SingleReservationFoyer> retrieveActiveSingleReservationFoyerByUsername(String username) {
        return singleReservationFoyerRepository.findSingleReservationFoyersByExpiredAndUsername(false,username);
    }
    @Override
    public String GenerateQrCodeWithUsername (Long id) {
        final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/srf"+id.toString()+".png";

        SingleReservationFoyer srf=singleReservationFoyerRepository.findById(id).get();


        String text = srf.getUsername()
                +"\n"
                +srf.getDateStart().toString()
                +"\n"
                +srf.getDateEnd().toString()
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
