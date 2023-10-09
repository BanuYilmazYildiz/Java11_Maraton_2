package com.banu.controller;

import com.banu.repository.entity.User;
import com.banu.service.UserService;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.util.List;

public class UserController {

    UserService userService;

    public UserController(){
        this.userService=new UserService();
    }

    public User createAccount(){
        String name = UtilityClass.stringDeger("Lutfen adınızı ve soyadınızı giriniz : ");
        String username = UtilityClass.stringDeger("Lutfen username giriniz : ");
        String photourl = UtilityClass.stringDeger("Profil fotoğrafı yukleyiniz : ");
        User user = User.builder()
                .name(name)
                .username(username)
                .photourl(photourl)
                .baseEntity(Constants.getBaseEntity())
                .build();
       return userService.save(user);
    }



    public User loginUser(){
        String username = UtilityClass.stringDeger("Lutfen kullanıcı adınızı giriniz : ");
        List<User> user = userService.findByColumnNameAndValue("username",username);
        if (!user.isEmpty()){
            System.out.println("Hoşgeldin "+ user.get(0).getName());
            return user.get(0);
        }else {
            return null;
        }

    }

}
