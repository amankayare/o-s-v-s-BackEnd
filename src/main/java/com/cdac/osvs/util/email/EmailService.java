package com.cdac.osvs.util.email;

import com.cdac.osvs.Exception.EmailException;
import com.cdac.osvs.dto.Election;
import com.cdac.osvs.dto.Voter;
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

    @Value("${app.domain}")
    String domain;

    public void sendMessageWithAttachment(File attachmentFile, Election election, Voter voter) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        String greeting = "Hiii " + voter.getFullName() + " \nHere is your login Link and secured file for Voting \n\nhttp://" + domain + ":" + angularPort + "/E-Ballot/api/login/" + election.getElectionId() + "\n\n\n";
        String electionDetails = "Election Name: " + election.getElectionName() + "\nStart Date: " + election.getStartDate()+ "\nEnd Date: " + election.getEndDate()+ "\nResult Date: " + election.getResultDate() + "\n\n\n";
        String loginCredentials = "Username: " + voter.getAdharNo() + "\nPassword: " + voter.getPassword() + "\n";
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(voter.getEmail());
        helper.setSubject(votingSubject);
        helper.setText(greeting + electionDetails + loginCredentials);


        helper.addAttachment("secured File", attachmentFile);

        emailSender.send(message);


    }


    public void sendMessageForVoterRegister(String to, String name) throws Exception {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            String text = "http://" + domain + ":" + angularPort + "/E-Ballot/api/addvoter/";

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("eballotonlinevotingsystem@gmail.com");
            helper.setTo(to);
            helper.setSubject(voterRegistration);
            helper.setText(text);


            emailSender.send(message);
        } catch (EmailException exception) {
            throw new EmailException(exception.getMessage());
        }
    }


    public void sendMessageForCandidateRegister(String to, String name) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        String text = "http://localhost:" + angularPort + "/E-Ballot/api/addcandidates/";

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eballotonlinevotingsystem@gmail.com");
        helper.setTo(to);
        helper.setSubject(candidateRegistration);
        helper.setText(text);


        emailSender.send(message);
    }

}
