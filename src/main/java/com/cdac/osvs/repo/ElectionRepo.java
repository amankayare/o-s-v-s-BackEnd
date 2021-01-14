package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Election;
@Repository
public interface ElectionRepo extends JpaRepository<Election,Integer> {

}
