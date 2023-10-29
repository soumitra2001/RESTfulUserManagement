package com.user.RESTfulUserManagement.service;

import com.user.RESTfulUserManagement.dto.SignUpInfo;
import com.user.RESTfulUserManagement.model.User;
import com.user.RESTfulUserManagement.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public String updateUser(User user, int id) {
        List<User> users =getAllUser();
        for(User myUser:users){
            if(myUser.getId()==id){
                myUser.setName(user.getName().isEmpty()? myUser.getName() : user.getName());
                myUser.setAge(user.getAge()==0 ? myUser.getAge() : user.getAge());
                myUser.setPassword(user.getPassword().isEmpty()? myUser.getPassword() : user.getPassword());
                userRepo.save(myUser);
                return "User successfully updated";
            }
        }
        return "Invalid user id";
    }

    private boolean isValidUser(int id){
        List<User> users =getAllUser();
        for(User myUser:users){
            if(myUser.getId()==id){
                return true;
            }
        }
        return false;
    }

    public String deleteUser(int id) {
        if(isValidUser(id)) {
            userRepo.deleteById(id);
            return "User successfully deleted having id=" + id;
        }
        return "Invalid user id";
    }

    public String deleteUserName(int id) {
        List<User> users =getAllUser();
        for(User user:users){
            if(user.getId()==id){
                user.setName(null);
                return "User name successfully deleted";
            }
        }
        return "Invalid user id";
    }

    public String deleteUserAge(int id) {
        List<User> users =getAllUser();
        for(User user:users){
            if(user.getId()==id){
                user.setAge(0);
                return "User age successfully deleted";
            }
        }
        return "Invalid user id";
    }


    public ResponseEntity<User> getUserById(int id) {
        List<User> users =getAllUser();
        for(User user:users){
            if(user.getId()==id){
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByEmail(username);

            }
        };
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String usernameNotFoundException(){
        return "Invalid username or password";
    }

}
