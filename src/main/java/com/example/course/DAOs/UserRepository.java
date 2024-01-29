package com.example.course.DAOs;

import com.example.course.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserId(UUID userId);
}