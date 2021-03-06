package com.cdac.osvs.repo;

import com.cdac.osvs.dto.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

    @Query("select o from Organization o where o.cin = :cinNo")
    public Organization getAlreadyExistOrganization(@Param(value = "cinNo") String cinNo);

    @Query("delete from Organization o where o.cin = :cinNo")
    public void deleteExistOrganization(@Param(value = "cinNo") String cinNo);
}
