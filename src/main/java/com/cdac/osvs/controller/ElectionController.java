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

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.service.ElectionService;



@RestController
@RequestMapping("/api/")
public class ElectionController {
	  
		@Autowired
		public ElectionService electionService;
		
		@CrossOrigin(origins = "*")
		@PostMapping(path = "addElection", consumes = "application/json", produces = "application/json")
		public String addElection(@RequestBody Election election) {
			electionService.insertElection(election);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@PutMapping(path = "updateElection", consumes = "application/json", produces = "application/json")
		public String modifyElection(@RequestBody  Election election) {
			electionService.update(election);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path = "deleteElection/{id}", consumes = "application/json", produces = "application/json")
		public String removeElection(@PathVariable Integer id) {
			electionService.deleteById(id);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path ="getElection/{id}", consumes = "application/json", produces = "application/json")
		public Election getElection(@PathVariable Integer id) {
			
			return electionService.selectById(id);
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path = "getAllElection", consumes = "application/json", produces = "application/json")
		public List<Election> getAllElection() {
			
			return electionService.selectAllElection();
		}
}
