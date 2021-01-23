package com.cdac.osvs.controller;


import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.dto.AdminStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateElectionEarned {


    @CrossOrigin(origins = "*")
    @PostMapping(path = "addAdmin", consumes = "application/json", produces = "application/json")
    public AdminStatus addAdmin(@RequestBody Admin admin) {
        Boolean isInserted = false;
        AdminStatus status = new AdminStatus();




        return status;
    }












}


