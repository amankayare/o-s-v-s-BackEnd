package com.cdac.osvs.controller;

import java.util.List;

import com.cdac.osvs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdac.osvs.service.AdminService;


@RestController
@RequestMapping("/api/")
public class AdminController {

    @Autowired
    public AdminService adminService;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "addAdmin", consumes = "application/json", produces = "application/json")
    public AdminStatus addAdmin(@RequestBody Admin admin) {
        Boolean isInserted = false;
        isInserted = adminService.insertAdmin(admin);
        if (isInserted) {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("admin added successfully");
            return status;
        } else {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("admin not added");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "modifyAdmin", consumes = "application/json", produces = "application/json")
    public AdminStatus modifyAdmin(@RequestBody Admin admin) {
        Boolean updated = adminService.update(admin);
        if (updated) {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("admin updated successfully");
            return status;
        } else {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("admin not found");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeAdmin/{id}", consumes = "application/json", produces = "application/json")
    public AdminStatus removeAdmin(@PathVariable Integer id) {
        Boolean isDeleted;
        isDeleted = adminService.deleteById(id);
        if (isDeleted) {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("admin deleted Successfully");
            return status;
        } else {
            AdminStatus status = new AdminStatus();
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("admin not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAdmin/{id}", consumes = "application/json", produces = "application/json")
    public AdminStatus getAdmin(@PathVariable Integer id) {
        AdminStatus status = new AdminStatus();
        Admin isFound = null;

        isFound = adminService.selectById(id);
        if (isFound != null) {
            status.setAdminId(isFound.getAdminId());
            status.setPassword(isFound.getPassword());
            status.setUsername(isFound.getUsername());
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("admin fetch succesfully");

        } else {

            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("admin not found");
            return status;
        }
        return status;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllAdmin", produces = "application/json")
    public List<Admin> getAllAdmin() {

        return adminService.selectAllAdmin();

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "adminLogin", produces = "application/json")
    public AdminStatus adminLogin(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password) {

        AdminStatus status = new AdminStatus();

        Admin fetchedAdmin = null;
        fetchedAdmin = adminService.checkLoginStatus(username, password);

        if (fetchedAdmin != null) {


            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Login Successfully");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Login Failed admin not found");
            return status;
        }
    }
}