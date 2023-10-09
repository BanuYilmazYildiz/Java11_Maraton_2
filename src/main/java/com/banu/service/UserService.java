package com.banu.service;

import com.banu.repository.UserRepository;
import com.banu.repository.entity.User;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    public UserService(){
        this.userRepository=new UserRepository();
    }

    public User save(User user) {
       return userRepository.save(user);
    }

    public List<User> findByColumnNameAndValue(String columnName, String value) {
        return userRepository.findByColumnNameAndValue(columnName,value);
    }

    public List<User> findAll(){
        return userRepository.findALl();
    }
}
