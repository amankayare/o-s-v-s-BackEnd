package com.cdac.osvs.controller;

import com.cdac.osvs.dto.*;
import com.cdac.osvs.service.AdminService;
import com.cdac.osvs.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/")

public class ContactController {


    @Autowired
    public ContactService contactService;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "addContact", produces = "application/json")
    public ContactStatus addContact(@RequestParam(value = "fullName") String fullName,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "query") String query) {
        Contact isInserted = null;
        ContactStatus status = new ContactStatus();
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setQuery(query);
        contact.setFullName(fullName);

        isInserted = contactService.insertContact(contact);
        if (isInserted != null) {
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("contact added successfully");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("contact not added");
            return status;
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getContact/{id}", produces = "application/json")
    public ContactStatus getContact(@PathVariable Integer id) {
        ContactStatus status = new ContactStatus();
        Contact isFound = null;

        isFound = contactService.selectById(id);
        if (isFound != null) {
            status.setContactId(isFound.getContactId());
            status.setEmail(isFound.getEmail());
            status.setQuery(isFound.getQuery());
            status.setFullName(isFound.getFullName());
            status.setStatus(Status.StatusType.SUCCESS);
            status.setMessage("Contact fetch succesfully");
            return status;

        } else {

            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("contact not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllContact", produces = "application/json")
    public List<Contact> getAllContact() {

        return contactService.selectAllContact();

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "removeContact/{id}", produces = "application/json")
    public ContactStatus removeContact(@PathVariable Integer id) {
        Boolean isDeleted;
        ContactStatus status = new ContactStatus();

        isDeleted = contactService.deleteById(id);
        if (isDeleted) {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Contact deleted Successfully");
            return status;
        } else {
            status.setStatus(Status.StatusType.FAILURE);
            status.setMessage("Contact not found");
            return status;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getAllContactsByEmail/{email}", produces = "application/json")
    public List<ContactStatus> getByEmail(@PathVariable String email) {
            List<Contact> list = contactService.selectAllByEmail(email);
            List<ContactStatus> finalList = new ArrayList<ContactStatus>();

            for (Contact contact : list) {
                ContactStatus status = new ContactStatus();
                status.setStatus(Status.StatusType.SUCCESS);
                status.setMessage("contact fetched successfully");
                status.setContactId(contact.getContactId());
                status.setEmail(contact.getEmail());
                status.setQuery(contact.getQuery());
                finalList.add(status);
            }
            return finalList;
    }
}