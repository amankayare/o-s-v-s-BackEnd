package com.cdac.osvs.repo;

import com.cdac.osvs.dto.Contact;
import com.cdac.osvs.dto.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> {

    @Query("select o from Contact o where o.email = :email")
    public List<Contact> getAllContactsByEmail(@Param(value = "email") String email);

}
