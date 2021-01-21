package com.cdac.osvs.dto;

public class CandidateRegisterStatus extends Status{
    private int registeredCandidateId;

    public int getRegisteredCustomerId() {
        return registeredCandidateId;
    }

    public void setRegisteredCustomerId(int registeredCustomerId) {
        this.registeredCandidateId = registeredCustomerId;
    }
}
