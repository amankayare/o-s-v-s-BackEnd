package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.osvs.dto.VoterElectionVoted;


@Repository
public interface VoterElectionVotedRepo extends JpaRepository<VoterElectionVoted, Integer> {


    @Modifying
    @Transactional
    @Query("update VoterElectionVoted v set v.isVoted=:voted where v.voterId=:vId and v.electionId=:eId")
    public void voted(@Param(value = "voted") int voted, @Param(value = "eId") int eId, @Param(value = "vId") int vId);


    @Query("select v from VoterElectionVoted v where v.voterId=:vId and v.electionId=:eId")
    public VoterElectionVoted getvoterElectionVotingStatus(@Param(value = "eId") int eId, @Param(value = "vId") int vId);


}
