package com.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNotificationEmail(String toAddress, String bookerName)  {
    //String toAddress = user.getEmail();
    String fromAddress = "break.booking.2021@gmail.com";
    String senderName = "Break Booking";
    String subject = "Booking confirmation";
    String content = "Dear [[Name]]<br>"
            + "Thank you for booking with us.<br>"
            + "Best regards<br>"
            + "Break Booking Team";

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[Name]]", bookerName);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    //String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

    //ontent = content.replace("[[URL]]", verifyURL);



    mailSender.send(message);
}

}
