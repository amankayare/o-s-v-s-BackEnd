package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Security;


@Repository
public interface SecurityRepo extends JpaRepository<Security,Integer> {

}
