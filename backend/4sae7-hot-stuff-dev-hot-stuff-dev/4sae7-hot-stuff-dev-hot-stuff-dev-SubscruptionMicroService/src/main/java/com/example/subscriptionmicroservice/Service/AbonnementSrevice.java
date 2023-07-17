package com.example.subscriptionmicroservice.Service;

import com.example.subscriptionmicroservice.EmailService.EmailService;
import com.example.subscriptionmicroservice.Entity.*;
import com.example.subscriptionmicroservice.Repository.Abonnement_F_Repository;
import com.example.subscriptionmicroservice.Repository.Abonnement_R_Repository;
import com.example.subscriptionmicroservice.Repository.PointMerciUserRepostory;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.currentTimeMillis;

@AllArgsConstructor
@Service

public class AbonnementSrevice implements IAbonnementService {

    MicroSU service;

    public final Abonnement_F_Repository abonnement_f_repository;
    public final Abonnement_R_Repository abonnement_r_repository;
    public final PointMerciUserRepostory pointMerciUserRepostory;
    private EmailService emailService;
    private MicroSU microSU;
    private MicroS microS;

    @Override
    public Date getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: " + formattedDate);

        return date;
    }

    @Override
    public int addAbonnementF(Abonnement_F abonnement_f, String username) {
        //User user = service.findUserByUsername(username);
      // boolean canAdd = this.checkcanAdd(abonnement_f);
        //System.out.println(canAdd);

        //if (!canAdd) {
          //return 0;
        List<Abonnement_F> list = abonnement_f_repository.findByExpiredAndUsername(false, username);
        if (list.stream().count() > 0 ) {
            return 0;
        }

        else{

            abonnement_f.setDateStart(new Date(currentTimeMillis()));
            abonnement_f.setExpired(false);

            if (abonnement_f.getTypeFoyer() == TypeFoyer.ANNUAL) {
                Date dt = abonnement_f.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 365);
                dt = c.getTime();
                abonnement_f.setDateEnd(dt);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                {
                    PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 600);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(600);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }

            } else if (abonnement_f.getTypeFoyer() == TypeFoyer.SEMESTER) {
                Date dt = abonnement_f.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 183);
                dt = c.getTime();
                abonnement_f.setDateEnd(dt);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                { PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 250);
                    pointMerciUserRepostory.save(pointMerciUser);}
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(250);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }


            } else if (abonnement_f.getTypeFoyer() == TypeFoyer.TRIMESTER) {
                Date dt = abonnement_f.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 92);
                dt = c.getTime();
                abonnement_f.setDateEnd(dt);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                { PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 100);
                    pointMerciUserRepostory.save(pointMerciUser);}
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(100);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }


            }
            abonnement_f.getDateStart().setTime(abonnement_f.getDateStart().getTime() + 3600000);
            abonnement_f.setUsername(username);
            microS.BookRoom(abonnement_f.getIdR());
            Abonnement_F abn = abonnement_f_repository.save(abonnement_f);


            return (int) abn.getId();

        }


    }

    @Override
    public Abonnement_F modifyAbonnementF(Abonnement_F abonnement_f) {
        return abonnement_f_repository.save(abonnement_f);
    }

    @Override
    public void deleteAbonnementF(long id) {
        Abonnement_F ab = abonnement_f_repository.findById(id).get();
        microS.BookRoomRf(ab.getIdR());
        abonnement_f_repository.deleteById(id);

    }


    @Override
    public List<Abonnement_F> retrieveAllF() {
        List<Abonnement_F> listAbon;
        listAbon = abonnement_f_repository.findAll();
        for (Abonnement_F abonnement_f : listAbon) {
            if (abonnement_f.getDateEnd().before(new Date(currentTimeMillis()))) {
                abonnement_f.setExpired(true);
                abonnement_f_repository.save(abonnement_f);
            }
        }


        return listAbon;
    }

    @Override
    public int addAbonnementR(Abonnement_R abonnement_r, String username) {
        //User user = service.findUserByUseranme(username);
        List<Abonnement_R> list = abonnement_r_repository.findByExpiredAndUsername(false, username);
        if (list.stream().count() > 0 ) {
            return 0;
        }

        else
         {
            abonnement_r.setDateStart(new Date(currentTimeMillis()));
            abonnement_r.setExpired(false);

            if (abonnement_r.getTypeRestaurant() == TypeRestaurant.ANNUAL) {
                Date dt = abonnement_r.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 365);
                dt = c.getTime();
                abonnement_r.setDateEnd(dt);
                abonnement_r.setNbJeton(1095);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                {
                    PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 600);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(600);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
            } else if (abonnement_r.getTypeRestaurant() == TypeRestaurant.MONTHLY) {
                Date dt = abonnement_r.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 30);
                dt = c.getTime();
                abonnement_r.setDateEnd(dt);
                abonnement_r.setNbJeton(93);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                { PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 50);
                    pointMerciUserRepostory.save(pointMerciUser);}
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(50);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
            } else if (abonnement_r.getTypeRestaurant() == TypeRestaurant.Weekly) {
                Date dt = abonnement_r.getDateStart();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 7);
                dt = c.getTime();
                abonnement_r.setDateEnd(dt);
                abonnement_r.setNbJeton(21);
                if (pointMerciUserRepostory.findByUsername(username)!=null)
                { PointMerciUser pointMerciUser= pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumber(pointMerciUser.getNumber()+ 10);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
                else {
                    PointMerciUser pointMerciUser = new PointMerciUser();
                    pointMerciUser.setNumber(10);
                    pointMerciUser.setUsername(username);
                    pointMerciUserRepostory.save(pointMerciUser);
                }
            }

            abonnement_r.setUsername(username);
           // abonnement_r.setSoldeJeton(abonnement_r.getSoldeJeton() + abonnement_r.getNbJeton());
            Abonnement_R abr = abonnement_r_repository.save(abonnement_r);

            return (int) abr.getId();
        }


    }

    @Override
    public Abonnement_R modifyAbonnementR(Abonnement_R abonnement_r, long id) {
        Abonnement_R abonnement_r1 = abonnement_r_repository.findById(id).orElse(null);
        abonnement_r1.setTypeRestaurant(abonnement_r1.getTypeRestaurant());

        return abonnement_r_repository.save(abonnement_r1);
    }

    @Override
    public void deleteAbonnementR(long id) {
        abonnement_r_repository.deleteById(id);

    }

    @Override
    public List<Abonnement_R> retrieveAllR() {
        List<Abonnement_R> listAbonn;
        listAbonn = abonnement_r_repository.findAll();
        for (Abonnement_R abonnement_r : listAbonn) {
            if (abonnement_r.getDateEnd().before(new Date(currentTimeMillis()))) {
                abonnement_r.setExpired(true);
                abonnement_r_repository.save(abonnement_r);
            }
        }


        return listAbonn;
    }

    @Override
    public boolean useJeton(String username, int nbJeton) {

        boolean canUseJeton = false;
        // User user = service.findUserByUserId(idUser);
        Abonnement_R abonR = abonnement_r_repository.findByUsernameAndExpired(username, false);

        if (abonR == null) {

            if (pointMerciUserRepostory.findByUsername(username) != null) {
                if (pointMerciUserRepostory.findByUsername(username).getNumJeton() >= nbJeton) {
                    canUseJeton = true;
                    PointMerciUser pointMerciUser = pointMerciUserRepostory.findByUsername(username);
                    pointMerciUser.setNumJeton(pointMerciUser.getNumJeton()-nbJeton);
                    pointMerciUserRepostory.save(pointMerciUser);
                }

            } else
                canUseJeton = false;

            genarateQRCOde(username, canUseJeton, null);
            return canUseJeton;
        } else {
            if (abonR.getNbJeton() >= nbJeton) {
                canUseJeton = true;
                abonR.setNbJeton(abonR.getNbJeton() - (int) nbJeton);
                if (abonR.getNbJeton() == 0) {
                    abonR.setExpired(true);
                }
                abonnement_r_repository.save(abonR);
                genarateQRCOde(username, canUseJeton, abonR);
                return canUseJeton;

            } else {
                if (pointMerciUserRepostory.findByUsername(username) != null) {
                    if (pointMerciUserRepostory.findByUsername(username).getNumJeton() + abonR.getNbJeton() >= nbJeton) {
                        PointMerciUser pointMerciUser = pointMerciUserRepostory.findByUsername(username);
                        pointMerciUser.setNumJeton(pointMerciUser.getNumJeton() - (nbJeton - abonR.getNbJeton()));
                        pointMerciUserRepostory.save(pointMerciUser);

                        abonR.setNbJeton(0);
                        abonR.setExpired(true);
                        abonnement_r_repository.save(abonR);

                        canUseJeton = true;
                        genarateQRCOde(username, canUseJeton, abonR);
                        return canUseJeton;


                    } else {
                        if (pointMerciUserRepostory.findByUsername(username).getNumJeton() >= nbJeton) {

                            PointMerciUser pointMerciUser = pointMerciUserRepostory.findByUsername(username);
                            pointMerciUser.setNumJeton(pointMerciUser.getNumJeton() - nbJeton);
                            pointMerciUserRepostory.save(pointMerciUser);


                            canUseJeton = true;
                            genarateQRCOde(username, canUseJeton, abonR);
                            return canUseJeton;
                        } else {
                            canUseJeton = false;
                            genarateQRCOde(username, canUseJeton, abonR);
                            return canUseJeton;
                        }
                    }
                }


            }

        }
        return canUseJeton;
    }

    @Override
    public int addJeton(String username, int nbJeton) {

            PointMerciUser pointMerciUser = pointMerciUserRepostory.findByUsername(username);
            pointMerciUser.setNumJeton(pointMerciUser.getNumJeton() +nbJeton);
            pointMerciUserRepostory.save(pointMerciUser);

        return 0;
    }

    boolean checkCanAddAbonF(String username) {

        List<Abonnement_F> list = abonnement_f_repository.findByExpiredAndUsername(false, username);
        if (list.stream().count() > 0 )
            return false;
    else
        return true;
    }

    boolean checkCanAddAbonR(Abonnement_R model) {

        List<Abonnement_R> list = abonnement_r_repository.findAllByExpiredAndUsername(false, model.getUsername());
        if (list.stream().count() > 0)
            return false;

        return true;
    }
    boolean checkcanAdd(Abonnement_F abonnement_f){
        List<Abonnement_R> list= abonnement_f_repository.findByUsername(abonnement_f.getUsername());


        if(list.isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    boolean genarateQRCOde(String username, boolean CanUseJeton, Abonnement_R abonR) {
        Date now = new Date();
        //User user = service.findUserByUserId(username);
        PointMerciUser pointMerciUser = pointMerciUserRepostory.findByUsername(username);


        String QRMessage = "NO";
        String SoldeAbonR = "";
        String filePath = "./src/main/resources/QRCodes/QRCode.png";

        if (abonR != null)
            SoldeAbonR = abonR.getNbJeton().toString();
        else
            SoldeAbonR = "0";


        if (CanUseJeton)
            QRMessage = "OK," + pointMerciUser.getNumJeton() + ", " + SoldeAbonR;
        else
            QRMessage = "KO" + pointMerciUser.getNumJeton() + ", " + SoldeAbonR;

        try {
            QRCodeGenerator.generateQRCodeImage(QRMessage, 100, 100, filePath);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Long countByColor(TypeFoyer tf) {
        return abonnement_f_repository.countAbonnement_FSByTypeFoyer(tf);

    }
    @Override
    public void email(long idUser) {

        Abonnement_F abonnement_f = abonnement_f_repository.findByExpired(false);

        if (abonnement_f.getExpired() == true) {

            User user = service.findUserByUserId(idUser);
            String subject = "subscription approved !";
            String text = "Hello, " + user.getNom() +" "+ user.getPrenom()+ "!\n\n"
                    + "This is an email to inform you that your Academic-Home subscription is From : "
                    + abonnement_f.getDateStart().toString()
                    + " To :"
                    + abonnement_f.getDateEnd().toString()
                    + "\n\n"
                    + "We are excited to see you guys !\n\n"
                    + "Best regards,\n"
                    + "The Hot-stuff-Dev Team";
            emailService.sendSimpleMessage(user.getEmail(), subject, text);
        }




    }





}





