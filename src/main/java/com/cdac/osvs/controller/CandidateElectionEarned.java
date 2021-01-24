package com.cdac.osvs.controller;


import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.dto.AdminStatus;
import com.cdac.osvs.service.CandidateElectionEarnedService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CandidateElectionEarned {

    @Autowired
    private CandidateElectionEarnedService candidateElectionEarnedService;
 
     @CrossOrigin(origins = "*")
     @GetMapping(path = "getCandidateElectionEarned/{eId}/{cId}",produces = "application/json")
    public int getCandidateElectionEarned(@PathVariable Integer eId,@PathVariable Integer cId) {
    	 System.out.println("28 controller");
    	return candidateElectionEarnedService.getVoteEarned(eId,cId);
    }
}


