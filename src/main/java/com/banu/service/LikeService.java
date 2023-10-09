package com.banu.service;

import com.banu.repository.LikeRepository;
import com.banu.repository.entity.Like;

public class LikeService {

    LikeRepository likeRepository;

    public LikeService(){
        this.likeRepository = new LikeRepository();
    }

    public Like save(Like like){
        return likeRepository.save(like);
    }
}
