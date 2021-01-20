package com.cdac.osvs.controller;


import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.service.OrganizationService;
import com.cdac.osvs.service.VoterService;
import com.cdac.osvs.util.RandomUtil;
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

    public String addOrganization(@RequestParam(value = "orgnizationName") String orgName,
                                  @RequestParam(value = "cin") String cin,
                                  @RequestParam(value = "excelFile") MultipartFile file
) {
        try {

            Organization organization = new Organization();
            organization.setCin(cin);
            organization.setOrgnizationName(orgName);


            String path = RandomUtil.organizationUploadDirectory + cin;
            Boolean a = new File(path).mkdirs();


            if (a) {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path +"\\"+ cin + ".xlsx");


            } else {
                Path fileNameAndPath = Paths.get(path + "/", cin + ".xlsx");

                Files.write(fileNameAndPath, file.getBytes());
                organization.setExcelFile(path +"\\"+ cin + ".xlsx");

            }

            organizationService.insertOrganization(organization);
            return "Success";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyOrganization", consumes = "application/json", produces = "application/json")
    public String modifyOrganization(@RequestBody Organization organization) {
        organizationService.updateOrganization(organization);
        return "updated";
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeOrganization/{id}", consumes = "application/json", produces = "application/json")
    public String removeAdmin(@PathVariable String id) {
        organizationService.deleteOrganizationByCinNo(id);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getOrganization/{id}", consumes = "application/json", produces = "application/json")
    public Organization getOrganization(@PathVariable Integer id) {

        return organizationService.getOrganizationById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getOrganizationByCinNo/{id}", consumes = "application/json", produces = "application/json")
    public Organization getOrganizationByCinNo(@PathVariable String id) {

        return organizationService.getOrganizationByCinNo(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllOrganization", consumes = "application/json", produces = "application/json")
    public List<Organization> getAllOrganization() {

        return organizationService.getAllOrganization();
    }


}
