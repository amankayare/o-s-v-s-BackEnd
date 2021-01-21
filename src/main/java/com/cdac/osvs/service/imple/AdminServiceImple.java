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
    public Boolean deleteById(int id) {

        try {
            adminRepo.deleteById(id);
            return true;

        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public Boolean insertAdmin(Admin admin) {
        Admin inserted = null;
        inserted = adminRepo.save(admin);
        if (inserted != null) {
            return true;

        } else {
            return false;
        }


    }

    @Override
    public Boolean update(Admin admin) {

        Optional<Admin> pt = adminRepo.findById(admin.getAdminId());

        if (pt.isPresent()) {
            adminRepo.save(admin);
            return true;
        } else {
            return false;
        }

    }

}
