package com.cdac.osvs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.dto.Organization;

@Repository
public interface ElectionRepo extends JpaRepository<Election, Integer> {


    @Query("select o from Election o where o.cin = :cinNo")
    public List<Election> getAlreadElectionByCin(@Param(value = "cinNo") String cinNo);
    
    
    @Query("select o from Election o ORDER BY o.electionId DESC")
    public List<Election> getAlreadElectionByElectionIdInDesc();
    
}
