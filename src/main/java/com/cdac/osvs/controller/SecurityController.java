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

import com.cdac.osvs.dto.Security;
import com.cdac.osvs.service.SecurityService;



@RestController
@RequestMapping("/api/")
public class SecurityController {
	  
		@Autowired
		public SecurityService securityService;
		
		@CrossOrigin(origins = "*")
		@PostMapping(path ="addSecurity", consumes = "application/json", produces = "application/json")
		public String addSecurity(@RequestBody  Security security) {
			securityService.insertSecurity(security);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@PutMapping(path = "updateSecurity", consumes = "application/json", produces = "application/json")
		public String modifySecurity(@RequestBody  Security security) {
			securityService.update(security);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path = "deleteSecurity/{id}", consumes = "application/json", produces = "application/json")
		public String removeSecurity(@PathVariable Integer id) {
			securityService.deleteById(id);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path = "getSecurity/{id}", consumes = "application/json", produces = "application/json")
		public Security getSecurity(@PathVariable Integer id) {
			
			return securityService.selectById(id);
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path = "getAllSecurity", consumes = "application/json", produces = "application/json")
		public List<Security> getAllSecurity() {
			
			return securityService.selectAllSecurity();
		}
}
