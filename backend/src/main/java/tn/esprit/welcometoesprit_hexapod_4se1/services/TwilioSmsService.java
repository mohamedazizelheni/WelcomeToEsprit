package tn.esprit.welcometoesprit_hexapod_4se1.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Value("${TWILIO_PHONE_NUMBER}")
//    private String phoneNumber;
//    public void sendSms(String to, String message) {
//        Twilio.init(accountSid, authToken);
//        Message.creator(new PhoneNumber(to), new PhoneNumber(phoneNumber), message).create();
//    }
}
