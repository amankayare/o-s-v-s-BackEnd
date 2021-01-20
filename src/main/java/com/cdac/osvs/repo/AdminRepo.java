package com.cdac.osvs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.osvs.dto.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

}
