package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Candidate;


public interface CandidateService {
    public List<Candidate> selectAllCandidate();

    public Candidate selectById(int id);

    public void deleteById(int id);

    public Boolean insertCandidate(Candidate candidate);

    public String update(Candidate candidate);

    public void addVoteEarned(int eId, int cId, int vId);

    public int getVoteEarned(int eId, int cId);
}
