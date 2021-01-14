package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Voter;


@Repository
public interface VoterRepo extends JpaRepository<Voter,Integer> {

}
