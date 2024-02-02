package com.example.course.Controllers;

import com.example.course.Entities.UserEntity;
import com.example.course.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/hello/{name}")
    public ResponseEntity<Object> helloWorld(@PathVariable String name){
        String returnValue = "Congratulations, "+name+"!.\nYou've successfully built your first API";
        ResponseEntity<Object> response = new ResponseEntity<>(returnValue, HttpStatus.OK);
        return response;
    }
    @PostMapping("/users/add")
    public ResponseEntity<Object>createUser(@RequestBody UserEntity userEntity){
        return userService.createUser(userEntity);
    }

    @PutMapping("users/update")
    public ResponseEntity<Object>updateUser(@RequestBody UserEntity userEntity){
        return userService.updateUser(userEntity);
    }
    @DeleteMapping("users/delete/{userId}")
    public ResponseEntity<Object>deleteUser(@PathVariable UUID userId){
        return userService.deleteUser(userId);
    }
    @GetMapping("users/find/{userId}")
    public ResponseEntity<Object>findUser(@PathVariable UUID userId){
        return userService.findUser(userId);
    }
    @GetMapping("users/findAll")
    public ResponseEntity<Object>findAll(){
        return userService.findAll();
    }


}