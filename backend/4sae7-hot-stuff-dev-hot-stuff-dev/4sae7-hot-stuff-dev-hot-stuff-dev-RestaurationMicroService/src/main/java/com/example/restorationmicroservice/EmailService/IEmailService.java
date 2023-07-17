package com.example.restorationmicroservice.EmailService;

public interface IEmailService {

         String sendSimpleMessage(String to, String subject, String text);

}
