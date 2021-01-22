package com.cdac.osvs.controller;


import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.dto.OrganizationStatus;
import com.cdac.osvs.dto.Status;
import com.cdac.osvs.service.OrganizationService;
import com.cdac.osvs.service.VoterService;
import com.cdac.osvs.util.RandomUtil;
import com.cdac.osvs.util.images.ConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class OrganizationController {


    @Autowired
    OrganizationService organizationService;

    @Autowired
    VoterService voterService;

    @CrossOrigin(origins = "*")

    //@PostMapping(path = "addOrganization", consumes = "application/json", produces = "application/json")
    @PostMapping(value = "addOrganization", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public OrganizationStatus addOrganization(@RequestParam(value = "orgnizationName") String orgName,
                                              @RequestParam(value = "cin") String cin,
                                              @RequestParam(value = "excelFile") MultipartFile file
    ) {
        OrganizationStatus status = new OrganizationStatus();
        try {

            Organization organization = new Organization();
            organization.setCin(cin);
            organization.setOrgnizationName(orgName);


            String path = RandomUtil.organizationUploadDirectory + cin;
            Boolean a = new File(path).mkdirs();


            if (a) {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path + "\\" + cin + ".xlsx");


            } else {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path + "\\" + cin + ".xlsx");

            }
            File fileForSendingMail = ConversionUtil.multipartToFileForOrganization(file, path + "\\" + cin + ".xlsx");

            voterService.readFileAndSendEmail(fileForSendingMail);


            Boolean added = organizationService.insertOrganization(organization);
            //after inserting and putting file on file system we are accessing that file

            if (added) {
                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("Organization added succesfully !!!");
                return status;
            } else {
                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("Organization already present so updation succesfully !!!");
                return status;
            }


        } catch (Exception e) {
            e.printStackTrace();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage(e.getMessage());
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyOrganization", produces = "application/json")
    public OrganizationStatus modifyOrganization(@RequestParam(value = "cin") String cin,
                                                 @RequestParam(value = "excelFile") MultipartFile file,
                                                 @RequestParam(value = "orgnizationName") String orgnizationName,
                                                 @RequestParam(value = "id") int id


    ) {

        try {
            Organization organization = new Organization();
            organization.setCin(cin);
            organization.setOrgnizationName(orgnizationName);
            organization.setId(id);

            String path = RandomUtil.organizationUploadDirectory + cin;
            Boolean a = new File(path).mkdirs();


            if (a) {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path + "\\" + cin + ".xlsx");


            } else {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path + "\\" + cin + ".xlsx");

            }

            Organization updatedOrganization = null;
            updatedOrganization = organizationService.updateOrganization(organization);
            OrganizationStatus status = new OrganizationStatus();

            if (updatedOrganization != null) {

                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("Organization updated successfully!!!");
                status.setCin(updatedOrganization.getCin());
                status.setExcelFile(updatedOrganization.getExcelFile());
                status.setOrgnizationName(updatedOrganization.getOrgnizationName());
                return status;
            } else {
                status.setStatus(Status.StatusType.FAILURE);
                status.setMessage("Organization Updation failed!! probably organization with this id not present");
                return status;
            }
        } catch (Exception exception) {
            OrganizationStatus status = new OrganizationStatus();

            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage(exception.getMessage());
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeOrganization/{id}", produces = "application/json")
    public Status removeAdmin(@PathVariable int id) {
        Status status = new Status();

        Boolean deleted = organizationService.deleteOrganizationById(id);

        if (deleted) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Organization deleted successfully!!!");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Organization deletion Failed probably organization with this id not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getOrganization/{id}", produces = "application/json")
    public OrganizationStatus getOrganization(@PathVariable Integer id) {
        OrganizationStatus status = new OrganizationStatus();
        Organization organization = null;
        organization = organizationService.getOrganizationById(id);
        if (organization != null) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Organization Fetched Successfully");
            status.setId(organization.getId());
            status.setExcelFile(organization.getExcelFile());
            status.setCin(organization.getCin());
            status.setOrgnizationName(organization.getOrgnizationName());
            return status;

        } else {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Organization updation Failed !!! probably organization with this id not found!!!");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getOrganizationByCinNo/{id}", produces = "application/json")
    public Organization getOrganizationByCinNo(@PathVariable String id) {

        return organizationService.getOrganizationByCinNo(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllOrganization", produces = "application/json")
    public List<Organization> getAllOrganization() {

        return organizationService.getAllOrganization();
    }


}
