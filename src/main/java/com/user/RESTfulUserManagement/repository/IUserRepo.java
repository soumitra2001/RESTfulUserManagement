package com.user.RESTfulUserManagement.repository;

import com.user.RESTfulUserManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
}
