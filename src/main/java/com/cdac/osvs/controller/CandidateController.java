package com.cdac.osvs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.service.CandidateService;
import com.cdac.osvs.util.RandomUtil;



@RestController
@RequestMapping("/api/")
public class CandidateController {
	  
		@Autowired
		public CandidateService candidateService;
		

	    @CrossOrigin(origins = "*")
	    @PostMapping(value = "addCandidate",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
	            produces = {MediaType.APPLICATION_JSON_VALUE} )

	    public String addOrganization(@RequestParam(value="fullName") String candidateName,
	                                  @RequestParam(value="email") String email,
	                                  @RequestParam(value="symbol") MultipartFile file,
	                                  @RequestParam(value="electionId") int eId, @RequestParam(value="adharNo") int adharNo) {
	

	        Candidate candidate=new Candidate();

	      //  byte[] byteArr = file.getBytes();
           System.out.println("53");
	        candidate.setFullName(candidateName);
	        candidate.setEmail(email);
	        candidate.setElectionId(eId);
	 
	        new File(RandomUtil.uploadDirectory+"Candidate/"+adharNo+"/").mkdir();

	    
	        
	       candidate.setSymbol(RandomUtil.uploadDirectory+"Candidate/"+adharNo+"/"+adharNo+".png");
	        
	   
	       System.out.println("53");
		Path fileNameAndPath = Paths.get(RandomUtil.uploadDirectory+"Candidate/"+adharNo+"/",adharNo+".png");

	       try{
	           Files.write(fileNameAndPath,file.getBytes());

	       }catch(IOException exception){
	           return exception.getMessage();
	       }

	        
	        candidateService.insertCandidate(candidate);
	        
	        return "Success";

	    }
	  


		@CrossOrigin(origins = "*")
		@PutMapping(path = "modifyCandidate", consumes = "application/json", produces = "application/json")
		public String modifyCandidate(@RequestBody Candidate candidate) {
			candidateService.update(candidate);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path = "removeCandidate/{id}", consumes = "application/json", produces = "application/json")
		public String removeCandidate(@PathVariable Integer id) {
			candidateService.deleteById(id);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path = "getCandidate/{id}", consumes = "application/json", produces = "application/json")
		public Candidate getCandidate(@PathVariable Integer id) {
			
			return candidateService.selectById(id);
		}
		
		@CrossOrigin(origins = "*")		
        @GetMapping(path="getAllCandidate", consumes = "application/json", produces = "application/json")
		public List<Candidate> getAllCandidate() {
			
			return candidateService.selectAllCandidate();
		}
		
		//after voter login
		@CrossOrigin(origins = "*")
		@PutMapping(path = "modifyCandidateVoteEarned", produces = "application/json")
		public String modifyCandidateVoteEarned(@RequestParam(value = "candidateId") int  candidateId,@RequestParam(value = "electionId")int  electionId,@RequestParam(value = "voterId") int  voterId) {
			candidateService.addVoteEarned(candidateId, electionId,voterId);
			return "voted";
		}
		
}
