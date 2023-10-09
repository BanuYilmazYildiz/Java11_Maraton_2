package com.banu.controller;

import com.banu.repository.entity.Like;
import com.banu.repository.entity.User;
import com.banu.service.LikeService;
import com.banu.service.PostService;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.time.LocalDate;

public class LikeController {

    LikeService likeService;

    PostService postService;

    public LikeController(){
        this.likeService=new LikeService();
        this.postService=new PostService();
    }

    public Like likePost(User user){
        postService.findAll().forEach(System.out::println);
        Long id = UtilityClass.longDeger("Beğenmek istediğiniz post id si = ");
        Like like = Like.builder()
                .date(LocalDate.now())
                .baseEntity(Constants.getBaseEntity())
                .userid(user.getId())
                .postid(id)
                .build();
        postService.findById(id).ifPresent(p->{
            p.setLikeCount(p.getLikeCount()+1);
            postService.update(p);
        });
        return likeService.save(like);
    }
}
