package com.cdac.osvs.repo;

import com.cdac.osvs.dto.CandidateElectionEarned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface CandidateElectionEarnedRepo extends JpaRepository<CandidateElectionEarned, Integer> {

    @Modifying
    @Transactional
    @Query("update CandidateElectionEarned c set c.voteEarned=:voteEarned where c.candidateId=:cId and c.electionId=:eId")
    public void increasesVote(@Param(value ="cId") int cId, @Param(value ="eId") int eId, @Param(value ="voteEarned") int voteEarned);

    @Transactional
    @Query("select  c from CandidateElectionEarned c where  c.candidateId=:cId and c.electionId=:eId")
    CandidateElectionEarned findByElectionIdAndCandidateID(@Param(value ="cId") int cId, @Param(value ="eId") int eId);
}
