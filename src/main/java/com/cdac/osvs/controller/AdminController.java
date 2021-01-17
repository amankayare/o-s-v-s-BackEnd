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

import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.service.AdminService;



@RestController
@RequestMapping("/api/")
public class AdminController {
	  
		@Autowired
		public AdminService adminService;
		
		@CrossOrigin(origins = "*")
		@PostMapping(path = "addAdmin", consumes = "application/json", produces = "application/json")
		public String addAdmin(@RequestBody Admin admin) {
			 adminService.insertAdmin(admin);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@PutMapping(path ="updateAdmin", consumes = "application/json", produces = "application/json")
		public String modifyAdmin(@RequestBody Admin admin) {
		String data= adminService.update(admin);
			return data;
		}
		
		@CrossOrigin(origins = "*")
		@DeleteMapping(path = "deleteAdmin/{id}", consumes = "application/json", produces = "application/json")
		public String removeAdmin(@PathVariable Integer id) {
			 adminService.deleteById(id);
			return "Success";
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path ="getAdmin/{id}", consumes = "application/json", produces = "application/json")
		public Admin getAdmin(@PathVariable Integer id) {
			
			return  adminService.selectById(id);
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping(path = "getAllAdmin", produces = "application/json")
		public List<Admin> getAllAdmin() {
			
			return  adminService.selectAllAdmin();
		}
}
