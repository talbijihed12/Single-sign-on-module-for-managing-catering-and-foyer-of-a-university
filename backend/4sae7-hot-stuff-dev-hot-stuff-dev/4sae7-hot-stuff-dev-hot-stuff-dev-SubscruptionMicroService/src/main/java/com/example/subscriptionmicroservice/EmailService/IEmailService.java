package com.example.subscriptionmicroservice.EmailService;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
}

