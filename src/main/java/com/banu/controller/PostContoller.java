package com.banu.controller;


import com.banu.repository.entity.Post;
import com.banu.repository.entity.User;
import com.banu.repository.views.viewLikePost;
import com.banu.service.PostService;
import com.banu.service.UserService;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.util.List;
public class PostContoller {
    PostService postService;
    ComputerController computerController;
    UserService userService;

    public PostContoller(){
        this.postService=new PostService();
        this.computerController =  new ComputerController();
        this.userService = new UserService();
    }

    public Post newPost(User user){
        computerController.findAll().forEach(System.out::println);
        Long id = UtilityClass.longDeger("Paylasmak istediginiz bilgisayar id si = ");
        Post post = Post.builder()
                .userid(user.getId())
                .shareddate(System.currentTimeMillis())
                .computerid(id)
                .likeCount(0L)
                .baseEntity(Constants.getBaseEntity())
                .build();
        return postService.save(post);
    }

    public List<Post> findAll(){
        return postService.findAll();
    }


    public List<viewLikePost> myLikePosts(User user){
        return postService.myLikePosts(user);
    }

    public List<Post> findPostsByUserId(){
        userService.findAll().forEach(System.out::println);
        Long id = UtilityClass.longDeger("Lütfen postlarını görmek istediginiz kullanıcı idsi seciniz : ");
        return  postService.findByColumnNameAndValue("id",id);
    }

}
