package com.cdac.osvs.repo;

import com.cdac.osvs.dto.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.osvs.dto.Voter;


@Repository
public interface VoterRepo extends JpaRepository<Voter, Integer> {


    @Query("select s from Voter s where s.adharNo=:adharNo and s.password=:password")
    public Voter voterIsPresent(@Param(value = "adharNo") long adharNo, @Param(value = "password") String password);


    @Query("select s from Voter s where s.adharNo=:adharNo or s.email=:email")
    public Voter voterIsAlreadyRegistered(@Param(value = "adharNo") long adharNo,@Param(value = "email") String email);

}
