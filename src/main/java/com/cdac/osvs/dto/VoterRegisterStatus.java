package com.cdac.osvs.dto;

public class VoterRegisterStatus extends Status {
    private int registeredVoterId;

    public int getRegisteredCustomerId() {
        return registeredVoterId;
    }

    public void setRegisteredCustomerId(int registeredCustomerId) {
        this.registeredVoterId = registeredCustomerId;
    }
}
