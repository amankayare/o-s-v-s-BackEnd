package com.cdac.osvs.service.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cdac.osvs.dto.Security;
import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.dto.Voter_Election_Voted;
import com.cdac.osvs.service.SecurityService;
import com.cdac.osvs.util.RandomUtil;
import com.cdac.osvs.util.email.EmailService;
import com.cdac.osvs.util.images.EncryptImage;
import com.cdac.osvs.util.images.SplitImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.repo.ElectionRepo;
import com.cdac.osvs.repo.VoterElectionVotedRepo;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.ElectionService;


@Service
public class ElectionServiceImple implements ElectionService {

    @Autowired
    private ElectionRepo electionRepo;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VoterElectionVotedRepo voterElectionVotedRepo;

    @Override
    public List<Election> selectAllElection() {
        List<Election> list = electionRepo.findAll();
        return list;
    }

    @Override
    public Election selectById(int id) {
        Optional<Election> opt = electionRepo.findById(id);

        return opt.get();
    }

    @Override
    public void deleteById(int id) {
        electionRepo.deleteById(id);


    }

    @Override
    public void insertElection(Election election) {


        electionRepo.save(election);


        //adding data into voter Election Voted to maintain who is already voted into particular election
        Set<Voter> voterList = election.getVoterList();

        for (Voter voter : voterList) {

            Voter_Election_Voted voterEletionVoted = new Voter_Election_Voted();

            voterEletionVoted.setElectionId(election.getElectionId());
            voterEletionVoted.setVoterId(voter.getVoterId());

            voterElectionVotedRepo.save(voterEletionVoted); //adding data into voterEletionVoted

            int voterId = voter.getVoterId();


            int randomKey = RandomUtil.generateRandom(8);



            //* Random image (original random image generation) *//
            String randomImageName = RandomUtil.generatingRandomAlphanumericFileName();
            File file = RandomUtil.generateRamdomImage(randomImageName);

            // save original image on file system and save path into database
            byte[] originalfile = new byte[(int) file.length()];
            String originalPath = RandomUtil.originalUploadDirectory+ voter.getAdharNo();

            Boolean a = new File(originalPath).mkdirs();
            String originalFinalPath =originalPath +"\\"+ voter.getAdharNo() + ".png";
            if (a) {
                System.out.println("yes");

                Path fileNameAndPath = Paths.get(originalPath + "/", voter.getAdharNo() + ".png");

                try {
                    Files.write(fileNameAndPath, originalfile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }


            } else {
                System.out.println("NO");
                Path fileNameAndPath = Paths.get(originalPath + "/", voter.getAdharNo() + ".png");

                try {
                    Files.write(fileNameAndPath, originalfile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }




            }

            //*spliting the original image and saving the share one into file System*//

            ArrayList<File> splitedFiles = SplitImage.breakImage(file);
            System.out.println("No of images:..........." + splitedFiles.size());
            byte[] databaseShare = new byte[(int) splitedFiles.get(0).getName().length()];

            String shareOnePath = RandomUtil.shareOneUploadDirectory+voter.getAdharNo();

            Path fileNameAndPath = Paths.get(shareOnePath + "/", voter.getAdharNo() + ".png");
            Boolean b = new File(shareOnePath).mkdirs();

            File f = splitedFiles.get(0);
            byte[] shareOneFile = new byte[(int) f.length()];

            try {
                Files.write(fileNameAndPath, shareOneFile);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            String shareOneFinalPath =shareOnePath +"\\"+ voter.getAdharNo() + ".png";


            //inserting all security related things into database
            Security security = new Security();
            security.setOrignalImg(originalFinalPath);
            security.setShareoneImg(shareOneFinalPath);
            security.setKeyValue(randomKey);
            security.setVoterId(voterId);
            security.setElectionId(election.getElectionId());

            securityService.insertSecurity(security);


            //*Encrypting second share and sending mail to voters with encrypted file*//
            File emailShare = splitedFiles.get(1);

            try {
                File encryptedEmailShare = EncryptImage.doEncrypt(emailShare, randomKey);

               // securityService.insertSecurity(security);

                for (Voter voter2 : voterList) {
                    emailService.sendMessageWithAttachment(voter2.getEmail(), voter2.getFullName(), encryptedEmailShare);
                    //emailService.sendMessageForVoterRegister(voter2.getEmail(),voter2.getFullName());

                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }


    }


    @Override
    public String update(Election election) {

        Optional<Election> pt = electionRepo.findById(election.getElectionId());

        if (pt.isPresent()) {
            electionRepo.save(election);
            return "Election is updated";
        } else {
            return "Election is not found";
        }

    }


}
