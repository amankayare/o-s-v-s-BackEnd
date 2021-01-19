package com.cdac.osvs.service;

import java.util.List;

import com.cdac.osvs.dto.Security;


public interface SecurityService {
    public List<Security> selectAllSecurity();

    public Security selectById(int id);

    public void deleteById(int id);

    public void insertSecurity(Security security);

    public String update(Security security);

    public Security getSecurityByVoterIdEletionId(int vId, int eId);
}
