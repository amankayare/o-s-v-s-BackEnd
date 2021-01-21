package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Admin;


public interface AdminService {
    public List<Admin> selectAllAdmin();

    public Admin selectById(int id);

    public Boolean deleteById(int id);

    public Boolean insertAdmin(Admin admin);

    public Boolean update(Admin admin);
}
