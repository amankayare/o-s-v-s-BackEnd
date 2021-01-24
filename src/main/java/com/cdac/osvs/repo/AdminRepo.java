package com.cdac.osvs.repo;

import com.cdac.osvs.dto.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {


    @Query("select s from Admin s where s.username=:username and s.password=:password")
    public Admin adminIsPresent(@Param(value = "username") String username, @Param(value = "password") String password);

}
