package com.example.course.Entities;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="user_id")
    private UUID userID;
}
