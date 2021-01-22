package com.cdac.osvs.service;

import com.cdac.osvs.dto.Candidate;
import com.cdac.osvs.dto.CandidateElectionEarned;

import java.util.List;

public interface CandidateElectionEarnedService {

    public List<CandidateElectionEarned> selectAllCandidateElectionEarned();

    public CandidateElectionEarned selectById(int id);

    public Boolean CandidateElectionEarnedDeleteById(int id);

    public Boolean insertCandidateElectionEarned(CandidateElectionEarned candidate);

    public CandidateElectionEarned update(CandidateElectionEarned candidate);

    public Boolean addVoteEarned(int eId, int cId, int vId);

    public int getVoteEarned(int eId, int cId);
}
