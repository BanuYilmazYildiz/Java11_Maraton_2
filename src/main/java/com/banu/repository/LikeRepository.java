package com.banu.repository;

import com.banu.repository.entity.Like;
import com.banu.utility.MyFactoryRepository;

public class LikeRepository extends MyFactoryRepository<Like,Long> {

    public LikeRepository(){
        super(new Like());
    }

}
