package com.cdac.osvs.dto;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "candidate_Election_Earned")

public class CandidateElectionEarned {


    @Id
    @Column(name = "Candidate_Election_EarnedId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateElectionEarnedId;

    @Column(nullable = false)
    private int electionId;
    @Column(nullable = false)
    private int candidateId;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int voteEarned;

    public int getCandidateElectionEarnedId() {
        return candidateElectionEarnedId;
    }

    public void setCandidateElectionEarnedId(int candidateElectionEarnedId) {
        this.candidateElectionEarnedId = candidateElectionEarnedId;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getVoteEarned() {
        return voteEarned;
    }

    public void setVoteEarned(int voteEarned) {
        this.voteEarned = voteEarned;
    }


}
