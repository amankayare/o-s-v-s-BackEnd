package com.cdac.osvs.service.imple;

import com.cdac.osvs.dto.Contact;
import com.cdac.osvs.repo.ContactRepo;
import com.cdac.osvs.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImple implements ContactService {

    @Autowired
    ContactRepo contactRepo;

    @Override
    public List<Contact> selectAllContact() {
        return contactRepo.findAll(Sort.by(Sort.Direction.DESC, "contactId"));
    }

    @Override
    public List<Contact> selectAllByEmail(String email) {
        return contactRepo.getAllContactsByEmail(email);
    }


    @Override
    public Contact selectById(int id) {
        Optional<Contact> opt = contactRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public Boolean deleteById(int id) {

        try {
            contactRepo.deleteById(id);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;

        }
    }

    @Override
    public Contact insertContact(Contact contact) {

        Contact insertedContact = contactRepo.save(contact);
        if (insertedContact != null) {

            return insertedContact;
        }
        return null;
    }

    @Override
    public Boolean update(Contact contact) {
      Optional<Contact> opt  = contactRepo.findById(contact.getContactId());

      if(opt.isPresent()){
          contactRepo.save(contact);
          return true;
      }else{
          return false;
      }
    }
}
