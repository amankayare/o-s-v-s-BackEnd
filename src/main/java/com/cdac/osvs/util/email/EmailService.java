package com.cdac.osvs.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessageWithAttachment(String to, String subject, String text, File attachmentFile) throws  Exception{
        MimeMessage message = emailSender.createMimeMessage();


        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);


        helper.addAttachment("Invoice", attachmentFile);

        emailSender.send(message);


    }

}
