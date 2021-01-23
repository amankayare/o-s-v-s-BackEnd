package com.cdac.osvs.controller;

import java.util.ArrayList;
import java.util.List;


import com.cdac.osvs.dto.ElectionStatus;
import com.cdac.osvs.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.service.ElectionService;


@RestController
@RequestMapping("/api/")
public class ElectionController {

    @Autowired
    public ElectionService electionService;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "addElection")
    public String addElection(@RequestBody Election election) {
        System.out.println(election.getCandidateList());
        System.out.println(election.getVoterList());
        electionService.insertElection(election);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyElection", consumes = "application/json", produces = "application/json")
    public String modifyElection(@RequestBody Election election) {
        electionService.update(election);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeElection/{id}", consumes = "application/json", produces = "application/json")
    public String removeElection(@PathVariable Integer id) {
        electionService.deleteById(id);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getElection/{id}", produces = "application/json")
    public ElectionStatus getElection(@PathVariable Integer id) {

        ElectionStatus status = new ElectionStatus();
        Election election = null;
        election = electionService.selectById(id);

        if (election != null) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Election Fetched successfully !!! ");
            status.setElectionId(election.getElectionId());
            status.setElectionName(election.getElectionName());
            status.setCin(election.getCin());
            status.setEndDate(election.getEndDate());
            status.setStartDate(election.getStartDate());
            status.setCandidateList(election.getCandidateList());
            status.setVoterList(election.getVoterList());
            status.setOrganization_id(election.getOrganization_id());
            status.setResultDate(election.getResultDate());
            return status;
        } else {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Election Fetching failed probably election wit this id not found !!! ");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllElection", produces = "application/json")
    public List<ElectionStatus> getAllElection() {

        List<Election> list = electionService.selectAllElection();

       List <ElectionStatus> status = new ArrayList<ElectionStatus>();


        for(Election election : list){
            ElectionStatus temp = new ElectionStatus();

            temp.setResultDate(election.getResultDate());
            temp.setElectionId(election.getElectionId());
            temp.setElectionName(election.getElectionName());
            temp.setOrganization_id(election.getOrganization_id());
            temp.setVoterList(election.getVoterList());
            temp.setCandidateList(election.getCandidateList());
            temp.setCin(election.getCin());
            temp.setStartDate(election.getStartDate());
            temp.setEndDate(election.getEndDate());
            status.add(temp);
        }
        return status;
    }


}






