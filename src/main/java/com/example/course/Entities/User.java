package com.example.course.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id")
    private UUID userID;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

}