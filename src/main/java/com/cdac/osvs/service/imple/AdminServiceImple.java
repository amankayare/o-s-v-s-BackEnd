package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.repo.AdminRepo;
import com.cdac.osvs.service.AdminService;


@Service
public class AdminServiceImple implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public List<Admin> selectAllAdmin() {
        List<Admin> list = adminRepo.findAll();
        return list;
    }

    @Override
    public Admin selectById(int id) {

        return adminRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        adminRepo.deleteById(id);

    }

    @Override
    public void insertAdmin(Admin admin) {
        adminRepo.save(admin);

    }

    @Override
    public String update(Admin admin) {

        Optional<Admin> pt = adminRepo.findById(admin.getAdminId());

        if (pt.isPresent()) {
            adminRepo.save(admin);
            return "Admin is updated";
        } else {
            return "Admin is not found";
        }

    }

}
