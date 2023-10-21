package com.user.RESTfulUserManagement.service;

import com.user.RESTfulUserManagement.dto.SignUpInfo;
import com.user.RESTfulUserManagement.model.User;
import com.user.RESTfulUserManagement.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepo userRepo;
    public ResponseEntity<String> addUser(SignUpInfo info) {
        List<User> users=getAllUser();
        for(User myUser:users){
            if(myUser.equals(info.getEmail())){
                return new ResponseEntity<>("User email already present", HttpStatus.BAD_REQUEST);
            }
        }
        User user=new User();
        user.setName(info.getName());
        user.setAge(info.getAge());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        userRepo.save(user);
        return new ResponseEntity<>("User sing up successful",HttpStatus.OK);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }
}
