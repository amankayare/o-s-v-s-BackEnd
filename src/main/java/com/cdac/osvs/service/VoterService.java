package com.cdac.osvs.service;

import java.io.File;
import java.util.List;

import com.cdac.osvs.dto.Voter;


public interface VoterService {
    public List<Voter> selectAllVoter();

    public Voter selectById(int id);

    public Boolean deleteById(int id);

    public Boolean insertVoter(Voter voter);

    public Voter update(Voter voter);

    public void readFileAndSendEmail(File excelFile);

    public Voter checkLoginStatus(long adharNo , String Password);

}
