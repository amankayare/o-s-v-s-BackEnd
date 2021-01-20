package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Admin;


public interface AdminService {
    public List<Admin> selectAllAdmin();

    public Admin selectById(int id);

    public void deleteById(int id);

    public void insertAdmin(Admin admin);

    public String update(Admin admin);
}
