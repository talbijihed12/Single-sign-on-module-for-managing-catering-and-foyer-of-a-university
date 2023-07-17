package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.dto.SmsRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceSMS {
    private final TwilioSmsSender smsSender;
    public ServiceSMS(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }


    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
