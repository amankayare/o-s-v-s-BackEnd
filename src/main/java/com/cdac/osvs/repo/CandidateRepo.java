package com.cdac.osvs.repo;

import com.cdac.osvs.dto.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.osvs.dto.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Integer> {

    @Modifying
    @Transactional
    @Query("update Candidate c set c.voteEarned=:voteEarned where c.candidateId=:cId and c.electionId=:eId")
    public void increasesVote(@Param("cId") int cId, @Param("eId") int eId, @Param("voteEarned") int voteEarned);

    @Query("select s from Candidate s where s.adharNo=:adharNo or s.email=:email")
    public Candidate candidateIsAlreadyRegistered(@Param(value = "adharNo") long adharNo, @Param(value = "email") String email);


}