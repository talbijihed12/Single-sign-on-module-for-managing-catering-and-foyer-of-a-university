package com.example.reservationmicroservice.Services;

import com.example.reservationmicroservice.EmailService.EmailService;
import com.example.reservationmicroservice.EmailService.EmailServiceImpl;
import com.example.reservationmicroservice.Entity.ReservationFoyer;
import com.example.reservationmicroservice.Entity.SingleReservationFoyer;
import com.example.reservationmicroservice.QRCode.QRCodeGenerator;
import com.example.reservationmicroservice.Repositories.IReservationFoyerRepository;
import com.example.reservationmicroservice.RoomMicroService.RoomDTO;
import com.example.reservationmicroservice.RoomMicroService.RoomService;
import com.example.reservationmicroservice.UserMicroService.UserDTO;
import com.example.reservationmicroservice.UserMicroService.UserService;
import com.example.reservationmicroservice.config.KeycloakConfig;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;

import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

@Service
@AllArgsConstructor
public class ReservationFoyerService implements IReservationFoyerService{
    private     IReservationFoyerRepository reservationFoyerRepository;
    private  RoomService roomService;
    private UserService userService;
    private EmailService emailService;
    @Override
    public ReservationFoyer addReservationFoyer(ReservationFoyer rf,String username) {
        rf.setUsername(username);
        return reservationFoyerRepository.save(rf);
    }
    @Override
    public ReservationFoyer addReservationFoyer(ReservationFoyer rf) {
        return reservationFoyerRepository.save(rf);
    }
    @Override
    public void deleteReservationFoyer(Long id) {
        reservationFoyerRepository.deleteById(id);
    }

    @Override
    public ReservationFoyer updateReservationFoyer(ReservationFoyer rf,Long id) {
        rf.setId(id);
        reservationFoyerRepository.save(rf);
        if (rf.getUsername() != null) {
            UserDTO userDTO =userService.retrieveUserInfo(rf.getUsername());
            String subject = "Academic-Home Reservation updated !";
            String text = "Hello," +userDTO.getNom()+"!\n\n"
                    + "This is an email to inform you that your Academic-Home Reservation From : "
                    + rf.getDateStart().toString()
                    + " To :"
                    + rf.getDateEnd().toString()
                    + " has been updated !"
                    +"\n\n"
                    + "We hope you're having a great day!\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(),subject,text);

        }
        return rf;
    }

    @Override
    public ReservationFoyer retrieveReservationFoyer(Long id) {
        ReservationFoyer rf= reservationFoyerRepository.findById(id).get();
        return rf;
    }

    @Override
    public List<ReservationFoyer> retrieveAllReservationFoyer() {
        List<ReservationFoyer> listReservationFoyer ;
        listReservationFoyer=reservationFoyerRepository.findAll();
        for (ReservationFoyer rf : listReservationFoyer) {
            if (rf.getDateEnd().before(new Date(currentTimeMillis()))) {
                rf.setExpired(true);
                reservationFoyerRepository.save(rf);
            }
        }
        return listReservationFoyer;

    }

    @Override
    public void approveReservationFoyer(Long id, List<Long> listIdRoom) {
        ReservationFoyer rf = retrieveReservationFoyer(id);
        List<Long> idlist = rf.getRoomIdList();
        for (Long idr : listIdRoom) {
            roomService.BookRoomRf(idr);
            idlist.add(idr);
        }
        rf.setRoomIdList(idlist);
        rf.setApproved(true);
        updateReservationFoyer(rf, id);
        if (rf.getUsername() != null) {
            UserDTO userDTO = new UserDTO();
            List<UserRepresentation> aa = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users()
                    .search(rf.getUsername());
            for (UserRepresentation a:aa) {
                userDTO.setEmail(a.getEmail());
                userDTO.setPrenom(a.getFirstName());
                userDTO.setNom(a.getLastName());
            }
            String subject = "Academic-Home Reservation approved !";
            String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                    + rf.getDateStart().toString()
                    + " To :"
                    + rf.getDateEnd().toString()
                    + " has been approved !"
                    + "\n\n"
                    + "We are excited to see you guys !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);
        }
    }

    @Override
    public void cancelReservationFoyer(Long id) {
        ReservationFoyer rf = retrieveReservationFoyer(id);
        List<Long> listRoom = rf.getRoomIdList();
        for (Long idR : listRoom) {
            roomService.UnbookRoom(idR);
        }
        deleteReservationFoyer(id);
        if (rf.getUsername() != null) {
            UserDTO userDTO = userService.retrieveUserInfo(rf.getUsername());
            String subject = "Academic-Home Reservation cancelled !";
            String text = "Hello, " + userDTO.getNom() +" "+ userDTO.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your demand for an Academic-Home Reservation From : "
                    + rf.getDateStart().toString()
                    + " To :"
                    + rf.getDateEnd().toString()
                    + " has been cancelled !"
                    + "\n\n"
                    + "We hope to see you soon !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(userDTO.getEmail(), subject, text);
        }
    }

    @Override
    public List<ReservationFoyer> retrieveReservationFoyerBetweenTwoDates(Date dd, Date df) {

        List<ReservationFoyer> list1 = reservationFoyerRepository.findReservationFoyerByDateStartBetween(dd,df);
        List<ReservationFoyer> list2 = reservationFoyerRepository.findReservationFoyersByDateEndBetween(dd,df);
        for (ReservationFoyer x : list1){
            if (!list2.contains(x))
                list2.remove(x);
        }
        return list2;
    }

    @Override
    public List<ReservationFoyer> retrieveApprovedReservationFoyerBetweenTwoDates(Date dd, Date df) {
        List<ReservationFoyer> list1 = retrieveReservationFoyerBetweenTwoDates(dd,df);
        List<ReservationFoyer> list2 = reservationFoyerRepository.findReservationFoyersByApproved(true);
        for (ReservationFoyer x : list1){
            if (list2.contains(x))
                list2.add(x);
        }
        return list2;
    }

    @Override
    public List<ReservationFoyer> retrieveReservationFoyerByUsername(String username) {
        return reservationFoyerRepository.findReservationFoyersByUsername(username);
    }

    @Override
    public List<ReservationFoyer> retrieveActiveReservationFoyer() {
        return reservationFoyerRepository.findReservationFoyersByExpired(false);
    }

    @Override
    public List<ReservationFoyer> retrieveActiveReservationFoyerByUsername(String username) {
        return reservationFoyerRepository.findReservationFoyersByExpiredAndUsername(false,username);
    }
    @Override
    public String GenerateQrCodeWithUsername (Long id) {
        final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/"+id.toString()+".png";

        ReservationFoyer rf=reservationFoyerRepository.findById(id).get();


        String text = rf.getUsername()
                    + "\n"
                    + rf.getNameGrp()
                    +"\n"
                    +rf.getDateStart().toString()
                    +"\n"
                    +rf.getDateEnd().toString()
                    +"\n"
                    +rf.getNbrFemale()
                    +"\n"
                    +rf.getNbrMale()
                    +"\n"
                    +rf.getNbrMixte()
                    + "\n\n";
        try {
            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(text,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return QR_CODE_IMAGE_PATH;
    }
    @Override
    public String GenerateQrCodeWithoutUsername (Long id){
        final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/"+id.toString()+".png";

        ReservationFoyer rf=reservationFoyerRepository.findById(id).get();


        String text =
                rf.getNameGrp()
                +"\n"
                +rf.getDateStart().toString()
                +"\n"
                +rf.getDateEnd().toString()
                +"\n"
                +rf.getNbrFemale()
                +"\n"
                +rf.getNbrMale()
                +"\n"
                +rf.getNbrMixte()
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
