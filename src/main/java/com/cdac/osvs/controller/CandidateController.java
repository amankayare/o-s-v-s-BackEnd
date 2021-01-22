package com.cdac.osvs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.cdac.osvs.dto.*;
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

import com.cdac.osvs.service.CandidateService;
import com.cdac.osvs.util.RandomUtil;


@RestController
@RequestMapping("/api/")
public class CandidateController {

    @Autowired
    public CandidateService candidateService;


    @CrossOrigin(origins = "*")
    @PostMapping(value = "addCandidate", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public CandidateStatus addCandidate(@RequestParam(value = "fullName") String candidateName,
                                        @RequestParam(value = "email") String email,
                                        @RequestParam(value = "symbol") MultipartFile file,
                                        @RequestParam(value = "electionId") int eId,
                                        @RequestParam(value = "adharNo") int adharNo,
                                        @RequestParam(value = "employeeId") int employeeId
    ) {

        try {


            //Status status = new Status();
            Candidate candidate = new Candidate();

            //byte[] byteArr = file.getBytes();
            System.out.println("53");
            candidate.setFullName(candidateName);
            candidate.setEmail(email);
            candidate.setElectionId(eId);
            candidate.setAdharNo(adharNo);
            candidate.setEmployeeId(employeeId);



            //   System.out.println(RandomUtil.candidateUploadDirectory + adharNo + "\\");
            String path = RandomUtil.candidateUploadDirectory + adharNo;
            Boolean a = new File(path).mkdirs();

            if (a) {
                System.out.println("yes");

                Path fileNameAndPath = Paths.get(path + "/", adharNo + ".png");

                Files.write(fileNameAndPath, file.getBytes());

                candidate.setSymbol(path + "\\" + adharNo + ".png");


                Boolean saved = candidateService.insertCandidate(candidate);
                CandidateStatus status = new CandidateStatus();
                if (saved) {
                    status.setStatus(Status.StatusType.SUCCESS);
                    status.setMessage("Registration successful!");
                    return status;

                } else {
                    status.setStatus(Status.StatusType.FAILURE);
                    status.setMessage("Candidate with this email or adhar no. already exist");
                    return status;
                }
            } else {
                System.out.println("NO");
                Path fileNameAndPath = Paths.get(path + "/", adharNo + ".png");

                Files.write(fileNameAndPath, file.getBytes());

                candidate.setSymbol(path + "\\" + adharNo + ".png");

                Boolean saved = candidateService.insertCandidate(candidate);
                CandidateStatus status = new CandidateStatus();

                if (saved) {
                    status.setStatus(Status.StatusType.SUCCESS);
                    status.setMessage("Registration successful!");
                    return status;

                } else {
                    status.setStatus(Status.StatusType.FAILURE);
                    status.setMessage("Candidate with this email or adhar no. already exist");
                    return status;
                }

            }


        } catch (Exception exception) {
            exception.printStackTrace();
            CandidateStatus status = new CandidateStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage(exception.getMessage());
            return status;
        }


    }


    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyCandidate", consumes = "application/json", produces = "application/json")
    public CandidateStatus modifyCandidate(@RequestBody Candidate candidate) {
        Candidate updatedCandidate = null;
        updatedCandidate = candidateService.update(candidate);
        CandidateStatus status = new CandidateStatus();
        if (updatedCandidate != null) {
            status.setVoteEarned(updatedCandidate.getVoteEarned());
            status.setCandidateId(updatedCandidate.getCandidateId());
            status.setEmployeeId(updatedCandidate.getEmployeeId());
            status.setCandidateId(updatedCandidate.getCandidateId());
            status.setSymbol(updatedCandidate.getSymbol());
            status.setEmail(updatedCandidate.getEmail());
            status.setAdharNo(updatedCandidate.getAdharNo());
            status.setFullName(updatedCandidate.getFullName());
            status.setListOfElection(updatedCandidate.getCandidateElectionList());
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Candidate updated successfully");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Candidate upadation Failed!!! probably candidate with this id not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeCandidate/{id}", produces = "application/json")
    public CandidateStatus removeCandidate(@PathVariable Integer id) {
        Boolean isDeleted = candidateService.deleteById(id);

        CandidateStatus status = new CandidateStatus();
        if (isDeleted) {

            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("candidate deleted successfully");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("candidate delete Failed probably candidate with this id not found");
            return status;

        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getCandidate/{id}", produces = "application/json")
    public CandidateStatus getCandidate(@PathVariable Integer id) {
        Candidate candidate = null;
        candidate = candidateService.selectById(id);
        if (candidate != null) {
            CandidateStatus status = new CandidateStatus();
            status.setCandidateId(candidate.getCandidateId());
            status.setAdharNo(candidate.getAdharNo());
            status.setEmail(candidate.getEmail());
            status.setSymbol(candidate.getSymbol());
            status.setElectionId(candidate.getElectionId());
            status.setEmployeeId(candidate.getEmployeeId());
            status.setVoteEarned(candidate.getVoteEarned());
            status.setFullName(candidate.getFullName());
            System.out.println(candidate.getCandidateElectionList());
            for (Election election : candidate.getCandidateElectionList()) {
                status.setListOfElection(candidate.getCandidateElectionList());
            }
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("candidate fetched successfully");

            return status;
        } else {
            CandidateStatus status = new CandidateStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("candidate not find with this id");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllCandidate", produces = "application/json")
    public List<Candidate> getAllCandidate() {

        return candidateService.selectAllCandidate();
    }

    //after voter login
    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyCandidateVoteEarned", produces = "application/json")
    public CandidateStatus modifyCandidateVoteEarned(@RequestParam(value = "candidateId") int candidateId, @RequestParam(value = "electionId") int electionId, @RequestParam(value = "voterId") int voterId) {
        Boolean voted = candidateService.addVoteEarned(candidateId, electionId, voterId);

        CandidateStatus status = new CandidateStatus();

        if (status != null) {

            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Voted Successfully");

            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Voting Failed");
            return status;
        }
    }

}
