package com.cdac.osvs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.osvs.dto.Candidate;
import com.cdac.osvs.dto.Security;
import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.repo.SecurityRepo;
import com.cdac.osvs.service.SecurityService;
import com.cdac.osvs.service.VoterService;
import com.cdac.osvs.util.RandomUtil;
import com.cdac.osvs.util.images.CompareImage;
import com.cdac.osvs.util.images.ConversionUtil;
import com.cdac.osvs.util.images.DecrytImage;
import com.cdac.osvs.util.images.MergeImage;

@RestController
@RequestMapping("/api/")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @Autowired
    private SecurityService securityService;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "addVoter", consumes = "application/json", produces = "application/json")
    public String addVoter(@RequestBody Voter voter) {
        voterService.insertVoter(voter);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyVoter", consumes = "application/json", produces = "application/json")
    public String modifyVoter(@RequestBody Voter voter) {
        voterService.update(voter);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeVoter/{id}", consumes = "application/json", produces = "application/json")
    public String removeVoter(@PathVariable Integer id) {
        voterService.deleteById(id);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getVoter/{id}", consumes = "application/json", produces = "application/json")
    public Voter getVoter(@PathVariable Integer id) {

        return voterService.selectById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllVoter", consumes = "application/json", produces = "application/json")
    public List<Voter> getAllVoter() {

        return voterService.selectAllVoter();
    }

    //Decryt image

    @CrossOrigin(origins = "*")
    @PostMapping(value = "goToPoll", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public String fileToDecryt(@RequestParam(value = "decrytFile") MultipartFile file,
                               @RequestParam(value = "electionId") int eId,
                               @RequestParam(value = "voterId") int vId) {

        try {


            byte[] byteArr = file.getBytes();


            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            Security security = securityService.getSecurityByVoterIdEletionId(vId, eId);

            int keyValue = security.getKeyValue();

            File decryptedShareTwo = DecrytImage.doDecrypt(convFile, keyValue);


            String shareOneByte = security.getShareoneImg();
            System.out.println("1");


            File shareOne = new File(RandomUtil.uploadDirectory + "shareOne.png");

            //  FileUtils.writeByteArrayToFile(shareOne, shareOneByte);


            System.out.println("2");

            File orignalImage = new File(RandomUtil.uploadDirectory + "orignalImage.png");

            //  FileUtils.writeByteArrayToFile(orignalImage, security.getOrignalImg());

            System.out.println("3");

            File mergedFile = MergeImage.merge(shareOne, decryptedShareTwo);

            System.out.println("114");

            Boolean isSame = CompareImage.isEqual(orignalImage, mergedFile);

            System.out.println("same");

            System.out.println(" is same " + isSame);

            if (isSame) {
                return "Success";
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}



