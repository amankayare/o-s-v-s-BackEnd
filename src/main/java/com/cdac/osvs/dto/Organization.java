package com.cdac.osvs.dto;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "orgnization_name",nullable = false)
    private String orgnizationName;

    @Column(name = "cin_no",nullable = false)
    private String cin;

    // file for read and mail

}
