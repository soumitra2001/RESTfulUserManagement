package com.user.RESTfulUserManagement.repository;

import com.user.RESTfulUserManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {

    UserDetails findByEmail(String username);
}
