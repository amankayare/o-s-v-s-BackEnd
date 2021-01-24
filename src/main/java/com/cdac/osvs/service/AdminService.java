package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.dto.Voter;


public interface AdminService {
    public List<Admin> selectAllAdmin();

    public Admin selectById(int id);

    public Boolean deleteById(int id);

    public Boolean insertAdmin(Admin admin);

    public Boolean update(Admin admin);
    public Admin checkLoginStatus(String  username, String password) ;

}
