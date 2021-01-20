package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.osvs.dto.Voter_Election_Voted;

public interface VoterElectionVotedRepo extends JpaRepository<Voter_Election_Voted, Integer> {


    @Modifying
    @Transactional
    @Query("update Voter_Election_Voted v set v.isVoted=:voted where v.voterId=:vId and v.electionId=:eId")
    public void voted(@Param("voted") int voted, @Param("eId") int eId, @Param("vId") int vId);


}
