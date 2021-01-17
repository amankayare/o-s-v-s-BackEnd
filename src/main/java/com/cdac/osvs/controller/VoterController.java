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

import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.service.VoterService;

@RestController
@RequestMapping("/api/")
public class VoterController {
	  
		@Autowired
		public VoterService voterService;
		
		@CrossOrigin(origins = "*")
		@PostMapping(path ="addVoter", consumes = "application/json", produces = "application/json")
		public String addVoter(@RequestBody Voter voter) {
			voterService.insertVoter(voter);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@PutMapping(path ="modifyVoter", consumes = "application/json", produces = "application/json")
		public String modifyVoter(@RequestBody Voter voter) {
			voterService.update(voter);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path ="removeVoter/{id}", consumes = "application/json", produces = "application/json")
		public String removeVoter(@PathVariable Integer id) {
			voterService.deleteById(id);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path ="getVoter/{id}", consumes = "application/json", produces = "application/json")
		public Voter getVoter(@PathVariable Integer id) {
			
			return voterService.selectById(id);
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path ="getAllVoter", consumes = "application/json", produces = "application/json")
		public List<Voter> getAllVoter() {
			
			return voterService.selectAllVoter();
		}
}
