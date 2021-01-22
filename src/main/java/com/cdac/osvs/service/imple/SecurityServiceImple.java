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
    private SecurityRepo securityRepo;

    @Override
    public List<Security> selectAllSecurity() {
        List<Security> list = securityRepo.findAll();
        return list;
    }

    @Override
    public Security selectById(int id) {
     //   Optional<Security> opt = SecurityRepo.findById(id);

        return securityRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        securityRepo.deleteById(id);

    }

    @Override
    public void insertSecurity(Security security) {
        securityRepo.save(security);


    }

    @Override
    public String update(Security security) {

        Optional<Security> pt = securityRepo.findById(security.getSecurityId());

        if (pt.isPresent()) {
            securityRepo.save(security);
            return "Security is updated";
        } else {
            return "Security is not found";
        }

    }

    @Override
    public Security getSecurityByVoterIdEletionId(int vId, int eId) {

        return securityRepo.getSecurityDetails(vId, eId);
    }


}
