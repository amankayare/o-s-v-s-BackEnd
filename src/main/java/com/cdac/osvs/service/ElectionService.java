package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.dto.Voter_Election_Voted;


public interface ElectionService {
    public List<Election> selectAllElection();
    public Election selectById(int id);
    public void deleteById(int id);
    public void insertElection(Election election);
    public String update(Election election);

}
