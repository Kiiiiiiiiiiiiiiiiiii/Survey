package com.example.surveyserver.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @Column
    private int branchId;

    @Column
    private String branchName;

    @Column
    private String phone;
}
