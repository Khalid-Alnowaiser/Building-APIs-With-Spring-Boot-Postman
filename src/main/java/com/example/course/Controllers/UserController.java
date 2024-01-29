package com.example.course.Controllers;

import com.example.course.Entities.UserEntity;
import com.example.course.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


}