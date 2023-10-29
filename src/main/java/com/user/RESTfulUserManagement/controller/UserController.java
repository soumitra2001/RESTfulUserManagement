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
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<String> addUser(@Valid @RequestBody SignUpInfo info){
        return userService.addUser(info);
    }

    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=userService.getAllUser();
        if(users.isEmpty())return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public String updateUser(@Valid @RequestBody User user,@PathVariable int id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/user")
    public String deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }

    @DeleteMapping("/user-name")
    public String deleteUserName(@RequestParam int id){
        return userService.deleteUserName(id);
    }

    @DeleteMapping("/user-age")
    public String deleteUserAge(@RequestParam int id){
        return userService.deleteUserAge(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal){
        return principal.getName();
    }

}
