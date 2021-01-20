package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cdac.osvs.dto.Security;
import com.cdac.osvs.repo.SecurityRepo;
import com.cdac.osvs.service.SecurityService;


@Service
public class SecurityServiceImple implements SecurityService {

    @Autowired
    private SecurityRepo SecurityRepo;

    @Override
    public List<Security> selectAllSecurity() {
        List<Security> list = SecurityRepo.findAll();
        return list;
    }

    @Override
    public Security selectById(int id) {
        Optional<Security> opt = SecurityRepo.findById(id);

        return opt.get();
    }

    @Override
    public void deleteById(int id) {
        SecurityRepo.deleteById(id);

    }

    @Override
    public void insertSecurity(Security security) {
        SecurityRepo.save(security);


    }

    @Override
    public String update(Security security) {

        Optional<Security> pt = SecurityRepo.findById(security.getSecurityId());

        if (pt.isPresent()) {
            SecurityRepo.save(security);
            return "Security is updated";
        } else {
            return "Security is not found";
        }

    }

    @Override
    public Security getSecurityByVoterIdEletionId(int vId, int eId) {

        return SecurityRepo.getSecurityDetails(vId, eId);
    }


}
