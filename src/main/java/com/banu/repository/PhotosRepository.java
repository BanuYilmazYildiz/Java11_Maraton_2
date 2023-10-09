package com.banu.repository;

import com.banu.repository.entity.Photos;
import com.banu.utility.MyFactoryRepository;

public class PhotosRepository extends MyFactoryRepository<Photos,Long> {

    public PhotosRepository(){
        super(new Photos());
    }

}
