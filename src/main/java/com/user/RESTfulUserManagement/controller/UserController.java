package com.user.RESTfulUserManagement.controller;

import com.user.RESTfulUserManagement.dto.SignUpInfo;
import com.user.RESTfulUserManagement.model.User;
import com.user.RESTfulUserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@Valid SignUpInfo info){
        return userService.addUser(info);
    }

    @GetMapping("/all-user")
    public ResponseEntity<List<User> getAllUser(){
        List<User> users=userService.getAllUser();
        if(users.isEmpty())return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public String updateUser(@Valid User user,  int id){

    }
}
