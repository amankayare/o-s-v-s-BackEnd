package com.cdac.osvs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.cdac.osvs.dto.*;
import com.cdac.osvs.repo.VoterElectionVotedRepo;
import com.cdac.osvs.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.osvs.service.SecurityService;
import com.cdac.osvs.service.VoterService;
import com.cdac.osvs.util.RandomUtil;
import com.cdac.osvs.util.images.CompareImage;
import com.cdac.osvs.util.images.DecrytImage;
import com.cdac.osvs.util.images.MergeImage;

@RestController
@RequestMapping("/api/")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ElectionService electionService;

    @Autowired
    private VoterElectionVotedRepo voterElectionVotedRepo;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "voterLogin", produces = "application/json")
    public VoterLoginStatus voterLogin(@RequestParam(value = "adharNo") long adharNo,
                                       @RequestParam(value = "password") String password,
                                       @RequestParam("electionId") int electionId) {


        System.out.println(adharNo);
        System.out.println(password);
        System.out.println(electionId);


        VoterLoginStatus status = new VoterLoginStatus();
        Election election = null;
        election = electionService.selectById(electionId);
        if (election == null) {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Login Failed !!! probably election with this id not found");
            return status;
        } else {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate today = LocalDate.now();
            System.out.println("System TIME: " + dtf.format(today));

            String stringStartDate = election.getStartDate();
            String stringEndDate = election.getEndDate();

            LocalDate startDate = LocalDate.parse(stringStartDate, dtf);
            LocalDate endDate = LocalDate.parse(stringEndDate, dtf);
            System.out.println("Converted End Date: " + endDate);
            System.out.println("Converted start Date: " + startDate);


            if (today.compareTo(startDate) >= 0 && today.compareTo(endDate) <= 0) {

                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("voting is  allowed today");

                try {
                    Voter fetchedVoter = null;
                    fetchedVoter = voterService.checkLoginStatus(adharNo, password);
                    if (fetchedVoter != null) {

                        //checking voter is already voted for this election or not
                        VoterElectionVoted vev = voterElectionVotedRepo.getvoterElectionVotingStatus(electionId, fetchedVoter.getVoterId());
                        if (vev.getIsVoted() == 1) {
                            status.setStatus(Status.StatusType.FAILURE);
                            status.setMessage("Voter is already voted for this election");
                            return status;

                        } else {
                            status.setStatus(Status.StatusType.SUCCESS);
                            status.setMessage("Login Successfully");
                            status.setEmail(fetchedVoter.getEmail());
                            status.setVoterId(fetchedVoter.getVoterId());
                            status.setAdharNo(fetchedVoter.getAdharNo());
                            status.setEmployeeId(fetchedVoter.getEmployeeId());
                            status.setFullName(fetchedVoter.getFullName());
                            status.setPassword(fetchedVoter.getPassword());
                            return status;

                        }


                    } else {
                        status.setStatus(Status.StatusType.FAILURE);
                        status.setMessage("Voter with this Adhar no and password not present");
                        return status;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    status.setStatus(Status.StatusType.FAILURE);
                    status.setMessage(exception.getMessage());
                    return status;
                }

            } else {
                status.setStatus(Status.StatusType.FAILURE);
                status.setMessage("voting is not allowed today probably election is not started or ended");
                return status;
            }


        }


    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "addVoter", consumes = "application/json", produces = "application/json")
    public VoterStatus addVoter(@RequestBody Voter voter) {
        VoterStatus status = new VoterStatus();

        Boolean voterRegister = voterService.insertVoter(voter);
        if (voterRegister) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Voter Successfully Added");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Voter is already present with this email or adhar no.");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyVoter", consumes = "application/json", produces = "application/json")
    public VoterStatus modifyVoter(@RequestBody Voter voter) {

        Voter updatedVoter = null;
        VoterStatus status = new VoterStatus();

        updatedVoter = voterService.update(voter);

        if (updatedVoter != null) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Voter Updated SuccessFully");
            status.setVoterId(updatedVoter.getVoterId());
            status.setAdharNo(updatedVoter.getAdharNo());
            status.setEmail(updatedVoter.getEmail());
            status.setEmployeeId(updatedVoter.getEmployeeId());
            status.setFullName(updatedVoter.getFullName());
            status.setPassword(updatedVoter.getPassword());

            for (Election election : status.getVoterElectionList()) {
                updatedVoter.getVoterElectionList().add(election);
            }
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Voter Updation Failed probably voter with this id not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeVoter/{id}", consumes = "application/json", produces = "application/json")
    public VoterStatus removeVoter(@PathVariable Integer id) {

        Boolean removed = voterService.deleteById(id);
        VoterStatus status = new VoterStatus();
        if (removed) {

            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Voter Deleted Successfully");

            return status;

        } else {

            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Voter Deletion Failed!!! probably voter with this id not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getVoter/{id}", produces = "application/json")
    public VoterStatus getVoter(@PathVariable Integer id) {
        Voter voter = null;
        VoterStatus status = new VoterStatus();

        voter = voterService.selectById(id);
        if (voter != null) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Voter Fetched Successfully!!!");
            status.setPassword(voter.getPassword());
            status.setVoterId(voter.getVoterId());
            status.setFullName(voter.getFullName());
            status.setEmployeeId(voter.getEmployeeId());
            status.setEmail(voter.getEmail());
            status.setAdharNo(voter.getAdharNo());
            status.setVoterElectionList(voter.getVoterElectionList());
            return status;

        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Voter Fetching Failed probably voter not found with this voter id!!!");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllVoter", produces = "application/json")
    public List<Voter> getAllVoter() {

        return voterService.selectAllVoter();
    }

    //Decryt image

    @CrossOrigin(origins = "*")
    @PostMapping(value = "goToPoll", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public Status fileToDecryt(@RequestParam(value = "decrytFile") MultipartFile file,
                               @RequestParam(value = "electionId") int electionId,
                               @RequestParam(value = "voterId") int voterId) {


        System.out.println(electionId);
        System.out.println(electionId);
        System.out.println(file.getOriginalFilename());
        System.out.println("----------------------------");

        Status status = new Status();
        try {
            System.out.println("1");

            Security security = securityService.getSecurityByVoterIdEletionId(voterId, electionId);
            System.out.println(security);

            int keyValue = security.getKeyValue();
            String shareOnePath = security.getShareoneImg();
            String originalPath = security.getOrignalImg();

            System.out.println("2");

            byte[] byteArr = file.getBytes();
            String randomFileName = RandomUtil.generatingRandomAlphanumericFileName();
            new File(RandomUtil.compareUploadDirectory).mkdirs();
            File convFile = new File(RandomUtil.compareUploadDirectory + randomFileName + ".png");
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            System.out.println("3");

            File decryptedShareTwo = DecrytImage.doDecrypt(convFile, keyValue);
            System.out.println("decryptedShareTwo-->"+decryptedShareTwo.getPath());

            System.out.println("4");

            File shareOne = new File(shareOnePath);

            //  FileUtils.writeByteArrayToFile(shareOne, shareOneByte);

            System.out.println("5");

            File orignalImage = new File(originalPath);

            //  FileUtils.writeByteArrayToFile(orignalImage, security.getOrignalImg());

            System.out.println("6");

            File mergedFile = MergeImage.merge(shareOne, decryptedShareTwo);
            System.out.println(mergedFile.getPath());

            System.out.println("same");

            Boolean isSame = CompareImage.isEqual(orignalImage, mergedFile);

            System.out.println("same");

            System.out.println(" is same " + isSame);

            if (isSame) {
                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("Valid image Credentials");
                return status;
            } else {
                status.setStatus(Status.StatusType.FAILURE);
                status.setMessage("Invalid image Credentials");
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: "+e.getMessage());
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage(e.getMessage());
            return status;

        }
    }
}



