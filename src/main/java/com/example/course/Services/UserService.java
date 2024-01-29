package com.example.course.Services;

import com.example.course.DAOs.UserRepository;
import com.example.course.Entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<Object> createUser(UserEntity userEntity) {
        try {
            if (userRepository.findByUserId(userEntity.getUserId()) != null) {
                return new ResponseEntity<>("User with user id: " + userEntity.getUserId() + " already exists", HttpStatus.CONFLICT);
            } else {
                userRepository.save(userEntity);
                return new ResponseEntity<>("Successfully added User", HttpStatus.CREATED);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more information", HttpStatus.BAD_REQUEST);
        }

    }
}