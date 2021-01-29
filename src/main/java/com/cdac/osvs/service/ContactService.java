package com.cdac.osvs.service;

import com.cdac.osvs.dto.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> selectAllContact();

    public Contact selectById(int id);

    public Boolean deleteById(int id);

    public Contact insertContact(Contact contact);

    public Boolean update(Contact contact);

    public List<Contact> selectAllByEmail(String email) ;

}
