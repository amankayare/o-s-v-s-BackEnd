package com.cdac.osvs.service;

import com.cdac.osvs.dto.Organization;

import java.io.File;
import java.util.List;

public interface OrganizationService {

    public Boolean insertOrganization(Organization org);

    public Organization updateOrganization(Organization org);

    public Boolean deleteOrganizationByCinNo(String cinNo);

    public Organization getOrganizationById(int id);

    public Organization getOrganizationByCinNo(String cinNo);

    public List<Organization> getAllOrganization();

    public void readFileAndSendEmail(File excelFile);

    public Boolean deleteOrganizationById(int id);


}
