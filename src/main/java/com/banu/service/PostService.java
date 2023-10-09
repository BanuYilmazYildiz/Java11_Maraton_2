package com.banu.service;

import com.banu.repository.PostRepository;

import com.banu.repository.entity.Post;
import com.banu.repository.entity.User;
import com.banu.repository.views.viewLikePost;

import java.util.List;
import java.util.Optional;

public class PostService {

    PostRepository postRepository;

    public PostService(){
        this.postRepository=new PostRepository();
    }

    public Post save(Post post){
        return  postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findALl();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }
    public Post update(Post post){
        return postRepository.update(post);
    }



    public List<viewLikePost> myLikePosts(User user) {
        return postRepository.myLikePosts(user);
    }

    public List<Post> findByColumnNameAndValue(String columnname, Long value){
        return postRepository.findByColumnNameAndValue(columnname,value);
    }

}
