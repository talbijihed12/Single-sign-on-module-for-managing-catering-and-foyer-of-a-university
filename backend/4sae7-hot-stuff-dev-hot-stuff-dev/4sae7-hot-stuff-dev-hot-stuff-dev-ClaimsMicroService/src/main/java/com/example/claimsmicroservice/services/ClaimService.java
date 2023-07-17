package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.dto.MailResponse;
import com.example.claimsmicroservice.dto.SmsRequest;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.webjars.NotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class ClaimService implements IServiceClaim {

    private ClaimRepository claimRepository;
    private JavaMailSender javaMailSender;
    private Configuration config;
    private TwilioSmsSender sender;


    @Override
    public Claim addClaim(Claim claim) {
        claim.setDateDiff(new Date());
        claim.setStatus(Status.EN_COURS);
        Claim newClaim = claimRepository.save(claim);
        sendEmail(newClaim);
        return newClaim;
    }

    @Override
    public List<Claim> findAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public Claim findClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    /*@Override
    public Claim changeStatus(Long id,Claim claimRequest) {

        Claim claim = claimRepository.findById(id).orElse(null);

        claim.setStatus(claimRequest.getStatus());
        return claimRepository.save(claim);
    }*/
    @Override
    public Claim changeStatus(Claim claim){
        return claimRepository.save(claim);

    }


    @Override
    public void cancelClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        if (claim.getStatus().equals(Status.EN_COURS) && getDaysBetween(id)>15){
            claim.setStatus(Status.ANNULER);
        }
        claimRepository.save(claim);
    }

    @Override
    public void removeClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        if (claim.getStatus().equals(Status.ANNULER)){
            claimRepository.deleteById(id);
        }
    }

    @Override
    public Integer nbClaimsResolu(Date dateDiff, Date dateRes) {
        return claimRepository.countByStatusAndDateResBetween(Status.RESOLU,dateDiff,dateRes);
    }
    /*@Override
    public Claim resolveClaim(Claim claim){
        claim.setDateRes(new Date());
        claim.setStatus(Status.RESOLU);
        Claim cl = claimRepository.save(claim);
        sendEmailResolved(cl);
        return cl;

    }*/
    @Override
    public Claim resolveClaim(Claim claim){
        claim.setDateRes(new Date());
        claim.setStatus(Status.RESOLU);
        Claim cl = claimRepository.save(claim);
       // sendEmailResolved(cl);
        return cl;
    }
    @Override
    public Claim returnClaim(Claim claim){
        claim.setStatus(Status.RETOUR);
        return claimRepository.save(claim);
    }
    /*@Override
    public Claim returnClaim(Long id,Claim claimRequest){
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setStatus(Status.RETOUR);
        claim.setCause(claimRequest.getCause());
        return claimRepository.save(claim);
    }*/
    /*@Override
    public Claim archiveClaim(Long id){
        Claim claim = claimRepository.findById(id).orElse(null);
        if (claim.getStatus()==Status.RESOLU){
            claim.setStatus(Status.ARCHIVER);
        }
        return claimRepository.save(claim);

    }*/
    @Override
    public Claim archiveClaim(Claim claim){
        claim.setStatus(Status.ARCHIVER);
        return claimRepository.save(claim);
    }
    @Override
    public Claim editClaim(Claim claim){
        claim.setStatus(Status.MODIFIER);
        return claimRepository.save(claim);
    }

    @Override
    public List<Claim> findReclamationByStatus() {

        return claimRepository.findByStatusIsNot(Status.ANNULER);
    }

    @Override
    public List<Claim> findByDateDiff(Date startDate, Date endDate) {
        return claimRepository.findClaimsByDateDiffBetween(startDate,endDate);
    }

    @Override
    public List<Claim> findByDateRes(Date startDate, Date endDate) {
        return claimRepository.findClaimsByDateResBetween(startDate,endDate);
    }


    @Override
    public List<Claim> findClaimsByTypeReclamation(TypeReclamation type) {
        return claimRepository.findClaimsByTypeReclamation(type);
    }


    @Override
    public List<Claim> search(String keywords) {
        return claimRepository.findByKeywords(keywords);

    }

    @Scheduled(cron = "0 0 8 * * ?")
    @Override
    public String Noticate() {
        String msg=" ";
        List<Claim> claims = claimRepository.findByStatusIsNot(Status.ANNULER);
        for (Claim claim : claims) {
            if (claim.getStatus().equals(Status.EN_COURS)) {
                msg = "!!! Claim RECEIVED! Time to verify";
            }

        }
        return msg;

    }


    @Override
    public List<Claim> findByUsername(String username) {

        return claimRepository.findClaimsByUsername(username);
    }


    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void deleteClaimByStatusResolu() {
        List<Claim> claims = claimRepository.findByStatusIsNot(Status.ANNULER);
        for (Claim c : claims) {
            claimRepository.deleteByStatus(Status.RESOLU);
        }
    }



    @Override

    public void banUserToAddClaim(String username,SmsRequest smsRequest) {
        List<Claim> claims = claimRepository.findClaimsByUsername(username);
        if (claimRepository.countClaimByUsername(username) > 2){
            for (Claim c : claims) {
                if (getDaysBetween(c.getId()) > 30 && c.getStatus() == Status.EN_COURS) {
                    claimRepository.deleteById(c.getId());
                    sender.sendSms(smsRequest);
                }
            }

        }else {
            throw new NotFoundException("User with username: " + username + " not found");

        }
    }


    public long getDaysBetween(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);

        LocalDate now = LocalDate.now(); // get the current date
        Date datt= Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        long daysDiff = datt.getTime() - claim.getDateDiff().getTime() ;//difference between two dates
        long diffInDays = TimeUnit.DAYS.convert(daysDiff, TimeUnit.MILLISECONDS);//convert result in days

        return diffInDays;
    }

    @Override
    public List<Claim> findClaimsByStatus(Status status) {
        return claimRepository.findClaimsByStatus(status);
    }
    @Override
    public List<Claim> findHistory() {
        return claimRepository.findClaimsByStatus(Status.ARCHIVER);
    }

    public MailResponse sendEmail(Claim request) {
        MailResponse response = new MailResponse();

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment

            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, request);

            helper.setTo(request.getEmail());
            helper.setText(html, true);
            helper.setSubject("Confirmation");
            javaMailSender.send(message);

            response.setMessage("mail send to : " + request.getEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }




    public MailResponse sendEmailResolved(Claim request) {
        MailResponse response = new MailResponse();

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment


            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template t = config.getTemplate("email-resolved.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, request);

            helper.setTo(request.getEmail());
            helper.setText(html, true);
            helper.setSubject("Claim Resolved");
            javaMailSender.send(message);

            response.setMessage("mail send to : " + request.getEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }








}