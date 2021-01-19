package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Security;


@Repository
public interface SecurityRepo extends JpaRepository<Security, Integer> {


    @Query("select s from Security s where s.voterId=:voterId and s.electionId=:electionId")
    public Security getSecurityDetails(@Param(value = "voterId") int vId, @Param(value = "electionId") int eId);

}
