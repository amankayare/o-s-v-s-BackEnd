package com.cdac.osvs.service;

import com.cdac.osvs.dto.Organization;

import java.util.List;

public interface OrganizationService {

    public void insertOrganization(Organization org);
    public void updateOrganization(Organization org);
    public void deleteOrganizationByCinNo(String cinNo);
    public Organization getOrganizationById(int id);
    public Organization getOrganizationByCinNo(String cinNo);
    public List<Organization> getAllOrganization();

}
