package com.cdac.osvs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.osvs.dto.Candidate;
import com.cdac.osvs.service.CandidateService;



@RestController
@RequestMapping("/api/")
public class CandidateController {
	  
		@Autowired
		public CandidateService candidateService;
		
		@CrossOrigin(origins = "*")
		@PostMapping(path = "addCandidate", consumes = "application/json", produces = "application/json")
		public String addCandidate(@RequestBody Candidate candidate) {
			candidateService.insertCandidate(candidate);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@PutMapping(path = "updateCandidate", consumes = "application/json", produces = "application/json")
		public String modifyCandidate(@RequestBody Candidate candidate) {
			candidateService.update(candidate);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path = "deleteCandidate/{id}", consumes = "application/json", produces = "application/json")
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
}
