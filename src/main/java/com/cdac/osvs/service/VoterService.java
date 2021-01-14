package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Voter;


public interface VoterService {
     public List<Voter> selectAllVoter();
     public Voter selectById(int id);
     public void deleteById(int id);
     public void insertVoter(Voter voter);
     public String update(Voter voter);
}
