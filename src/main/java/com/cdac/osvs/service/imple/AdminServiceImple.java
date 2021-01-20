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
    private AdminRepo AdminRepo;

    @Override
    public List<Admin> selectAllAdmin() {
        List<Admin> list = AdminRepo.findAll();
        return list;
    }

    @Override
    public Admin selectById(int id) {
        Optional<Admin> opt = AdminRepo.findById(id);

        return opt.get();
    }

    @Override
    public void deleteById(int id) {
        AdminRepo.deleteById(id);

    }

    @Override
    public void insertAdmin(Admin admin) {
        AdminRepo.save(admin);

    }

    @Override
    public String update(Admin admin) {

        Optional<Admin> pt = AdminRepo.findById(admin.getAdminId());

        if (pt.isPresent()) {
            AdminRepo.save(admin);
            return "Admin is updated";
        } else {
            return "Admin is not found";
        }

    }

}
