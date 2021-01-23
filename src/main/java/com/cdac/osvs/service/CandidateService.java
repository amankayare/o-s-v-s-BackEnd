package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Candidate;


public interface CandidateService {
    public List<Candidate> selectAllCandidate();

    public Candidate selectById(int id);

    public Boolean deleteById(int id);

    public Boolean insertCandidate(Candidate candidate);

    public Candidate update(Candidate candidate);


}
