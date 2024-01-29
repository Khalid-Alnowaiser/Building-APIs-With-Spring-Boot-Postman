package com.example.course.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name="age")
    private int age;
    @Column(name = "name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name = "password")
    private String password;
}