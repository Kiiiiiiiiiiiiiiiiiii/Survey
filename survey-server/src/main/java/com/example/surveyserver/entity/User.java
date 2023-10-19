package com.example.surveyserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String company;

    @Column
    private String email;

    @Column
    private String industry;

    @Column
    private Date dateCreated;

    @Column
    private int branchId;

    @Column
    private String branchName;

}
