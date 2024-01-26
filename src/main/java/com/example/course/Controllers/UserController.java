package com.example.course.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class UserController {

    @GetMapping("/hello/{name}")
    public ResponseEntity<Object> helloWorld(@PathVariable String name){
        try {
            return new ResponseEntity<>("Hello " + name + ", you've successfully made your first API!", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected error has occurred, please check the logs for more info",HttpStatus.BAD_REQUEST);
        }


    }
}
