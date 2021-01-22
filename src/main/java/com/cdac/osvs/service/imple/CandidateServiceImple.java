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

        if (opt.isPresent()) {
            return opt.get();

        }
        return null;
    }

    @Override
    public Boolean deleteById(int id) {

        try {
            candidateRepo.deleteById(id);

            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }


    }

    @Override
    public Boolean insertCandidate(Candidate candidate) {

        Candidate candidateIsAlreadyRegistered = candidateRepo.candidateIsAlreadyRegistered(candidate.getAdharNo(), candidate.getEmail());
        if (candidateIsAlreadyRegistered == null) {
            candidateRepo.save(candidate);
            System.out.println("candidate was not present so inserting...");

            return true;
        } else {
            System.out.println("candidate is already present");
            return false;
        }

    }


    @Override
    public Candidate update(Candidate candidate) {

        Optional<Candidate> pt = candidateRepo.findById(candidate.getCandidateId());

        if (pt.isPresent()) {
            Candidate updatedCandidate = candidateRepo.save(candidate);
            return updatedCandidate;
        } else {
            return null;
        }

    }




}