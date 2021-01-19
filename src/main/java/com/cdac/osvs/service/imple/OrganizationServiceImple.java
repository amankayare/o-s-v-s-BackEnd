package com.cdac.osvs.service.imple;

import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.repo.OrganizationRepo;
import com.cdac.osvs.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImple implements OrganizationService {

    @Autowired
    OrganizationRepo organizationRepo;

    @Override
    public void insertOrganization(Organization org) {
        Organization organization = null;
        organization = organizationRepo.getAlreadyExistOrganization(org.getCin());

        if (organization == null) {
            organizationRepo.save(org);
        } else {
            System.out.println("Else .." + organization);
            org.setId(organization.getId());
            updateOrganization(org);
        }
        //  organizationRepo.save(org);

    }

    @Override
    public void updateOrganization(Organization org) {
        Optional<Organization> opt = organizationRepo.findById(org.getId());
        if (opt.isPresent()) {
            System.out.println("opt.isPresent() ..");

            organizationRepo.save(org);
        }

    }

    @Override
    public void deleteOrganizationByCinNo(String cinNo) {
        organizationRepo.deleteExistOrganization(cinNo);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepo.getOne(id);
    }

    @Override
    public Organization getOrganizationByCinNo(String cinNo) {
        return organizationRepo.getAlreadyExistOrganization(cinNo);
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepo.findAll();
    }
}
