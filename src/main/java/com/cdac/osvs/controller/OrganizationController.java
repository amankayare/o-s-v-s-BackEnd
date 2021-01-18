package com.cdac.osvs.controller;


import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.service.OrganizationService;
import com.cdac.osvs.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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
    @PostMapping(value = "addOrganization",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )

    public String addOrganization(@RequestParam(value="orgnizationName") String orgName,
                                  @RequestParam(value="cin") String cin,
                                  @RequestParam(value="excelFile") MultipartFile file

                                 ) {
try {
    System.out.println("//////////////////////////");

    System.out.println(orgName);
    System.out.println(cin);
    System.out.println(file);
    System.out.println("//////////////////////////");
        Organization organization = new Organization();

        byte[] byteArr = file.getBytes();

        organization.setExcelFile(byteArr);
        organization.setCin(cin);
        organization.setOrgnizationName(orgName);


        organizationService.insertOrganization(organization);


        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();


        voterService.readFileAndSendEmail(convFile);
        return "Success";

    }
    catch(Exception e){
           return e.getMessage();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path ="modifyOrganization", consumes = "application/json", produces = "application/json")
    public  String modifyOrganization(@RequestBody Organization organization) {
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
    @GetMapping(path ="getOrganization/{id}", consumes = "application/json", produces = "application/json")
    public Organization getOrganization(@PathVariable Integer id) {

        return  organizationService.getOrganizationById(id);
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path ="getOrganizationByCinNo/{id}", consumes = "application/json", produces = "application/json")
    public Organization getOrganizationByCinNo(@PathVariable String id) {

        return  organizationService.getOrganizationByCinNo(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllOrganization", consumes = "application/json", produces = "application/json")
    public List<Organization> getAllOrganization() {

        return  organizationService.getAllOrganization();
    }


}
