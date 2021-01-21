package com.cdac.osvs.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${email.voting.subject}")
    String votingSubject;

    ;
    @Value("${email.voter.register.subject}")
    String voterRegistration;

    @Value("${angular.port}")
    String angularPort;

    @Value("${email.candidate.register.subject}")
    String candidateRegistration;


    public void sendMessageWithAttachment(String to, String name, File attachmentFile) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        String text = "Hiii " + name;

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(to);
        helper.setSubject(votingSubject);
        helper.setText(text);


        helper.addAttachment("Invoice", attachmentFile);

        emailSender.send(message);


    }


    public void sendMessageForVoterRegister(String to, String name) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        String text = "http://localhost:" + angularPort + "/E-Ballot/api/addVoter/";

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(to);
        helper.setSubject(voterRegistration);
        helper.setText(text);


        emailSender.send(message);
    }


    public void sendMessageForCandidateRegister(String to, String name) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        String text = "http://localhost:" + angularPort + "/E-Ballot/api/addCandidate/";

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(to);
        helper.setSubject(candidateRegistration);
        helper.setText(text);


        emailSender.send(message);
    }

}
