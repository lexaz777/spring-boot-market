package com.geekbrains.springbootproject.services;


import com.geekbrains.springbootproject.entities.SystemUser;
import com.geekbrains.springbootproject.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
