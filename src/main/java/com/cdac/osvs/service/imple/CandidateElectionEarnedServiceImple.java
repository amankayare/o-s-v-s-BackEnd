package com.cdac.osvs.service.imple;

import com.cdac.osvs.dto.CandidateElectionEarned;
import com.cdac.osvs.dto.VoterElectionVoted;
import com.cdac.osvs.repo.CandidateElectionEarnedRepo;
import com.cdac.osvs.repo.VoterElectionVotedRepo;
import com.cdac.osvs.service.CandidateElectionEarnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CandidateElectionEarnedServiceImple implements CandidateElectionEarnedService {

    @Autowired
    CandidateElectionEarnedRepo candidateElectionEarnedRepo;
    @Autowired
    VoterElectionVotedRepo voterElectionVotedRepo;

    @Override
    public List<CandidateElectionEarned> selectAllCandidateElectionEarned() {
        return candidateElectionEarnedRepo.findAll();
    }

    @Override
    public CandidateElectionEarned selectById(int id) {
        Optional<CandidateElectionEarned> opt = candidateElectionEarnedRepo.findById(id);
        if (opt.isPresent()) {

            return opt.get();
        }
        return null;
    }

    @Override
    public Boolean CandidateElectionEarnedDeleteById(int id) {

        try {
            candidateElectionEarnedRepo.deleteById(id);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;

        }
    }

    @Override
    public Boolean insertCandidateElectionEarned(CandidateElectionEarned candidateElectionEarned) {

        try {
            candidateElectionEarnedRepo.save(candidateElectionEarned);
            return true;
        } catch (Exception exception) {
            return false;

        }


    }

    @Override
    public CandidateElectionEarned update(CandidateElectionEarned candidateElectionEarned) {

        Optional<CandidateElectionEarned> opt = candidateElectionEarnedRepo.findById(candidateElectionEarned.getCandidateElectionEarnedId());

        if (opt.isPresent()) {

            return candidateElectionEarnedRepo.save(candidateElectionEarned);

        }
        return null;

    }

    @Override
    public Boolean addVoteEarned(int electionId, int candidateId, int voterId) {

        try {
            CandidateElectionEarned candidateElectionEarned = null;
            candidateElectionEarned = candidateElectionEarnedRepo.findByElectionIdAndCandidateID(candidateId, electionId);

            //if no entry is there so insert new entry
            if (candidateElectionEarned == null) {
                CandidateElectionEarned newObj = new CandidateElectionEarned();
                newObj.setCandidateId(candidateId);
                newObj.setElectionId(electionId);
                newObj.setVoteEarned(1);
                candidateElectionEarnedRepo.save(newObj);

                //voter is already voted or not is maintaining here
                voterElectionVotedRepo.voted(1, electionId, voterId);
                return true;
            } else {
                int vEarned = getVoteEarned(electionId, candidateId);
                vEarned = vEarned + 1;
                System.out.println(vEarned);

                //candidate vote earning maintaing
                candidateElectionEarnedRepo.increasesVote(candidateId, electionId, vEarned);

                //voter is already voted or not is maintaining here
                voterElectionVotedRepo.voted(1, electionId, voterId);
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public int getVoteEarned(int electionId, int candidateId) {

        CandidateElectionEarned candidateElectionEarned = candidateElectionEarnedRepo.findByElectionIdAndCandidateID(candidateId, electionId);

        return candidateElectionEarned.getVoteEarned();

    }

}

