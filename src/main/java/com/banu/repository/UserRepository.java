package com.banu.repository;

import com.banu.repository.entity.User;
import com.banu.utility.MyFactoryRepository;



public class UserRepository extends MyFactoryRepository<User,Long> {

    public UserRepository(){
        super(new User());

    }


}
