package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

}