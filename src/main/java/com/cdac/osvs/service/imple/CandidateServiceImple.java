package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Candidate;
import com.cdac.osvs.repo.CandidateRepo;
import com.cdac.osvs.repo.VoterElectionVotedRepo;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.CandidateService;


@Service
public class CandidateServiceImple implements CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;


    @Autowired
    private VoterElectionVotedRepo voterElectionVotedRepo;


    @Override
    public List<Candidate> selectAllCandidate() {
        List<Candidate> list = candidateRepo.findAll();
        return list;
    }

    @Override
    public Candidate selectById(int id) {
        Optional<Candidate> opt = candidateRepo.findById(id);

        return opt.get();
    }

    @Override
    public void deleteById(int id) {
        candidateRepo.deleteById(id);

    }

    @Override
    public Boolean insertCandidate(Candidate candidate) {

        Candidate candidateIsAlreadyRegistered     = candidateRepo.candidateIsAlreadyRegistered(candidate.getAdharNo(), candidate.getEmail());
       if(candidateIsAlreadyRegistered == null){
           candidateRepo.save(candidate);
           System.out.println("candidate was not present so inserting...");

            return true;
       }else {
           System.out.println("candidate is already present");
           return false;
       }

    }


    @Override
    public String update(Candidate candidate) {

        Optional<Candidate> pt = candidateRepo.findById(candidate.getCandidateId());

        if (pt.isPresent()) {
            candidateRepo.save(candidate);
            return "Candidate is updated";
        } else {
            return "Candidate is not found";
        }

    }

    @Override
    public void addVoteEarned(int eId, int cId, int vId) {
        int vEarned = getVoteEarned(eId, cId);
        vEarned = vEarned + 1;
        System.out.println(vEarned);


        candidateRepo.increasesVote(cId, eId, vEarned);

        voterElectionVotedRepo.voted(1, eId, vId);
    }

    @Override
    public int getVoteEarned(int eId, int cId) {

        return candidateRepo.getOne(cId).getVoteEarned();
    }

}
