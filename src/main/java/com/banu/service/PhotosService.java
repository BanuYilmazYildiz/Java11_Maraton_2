package com.banu.service;

import com.banu.repository.PhotosRepository;

public class PhotosService {
    PhotosRepository photosRepository;

    public PhotosService(){
        this.photosRepository=new PhotosRepository();
    }
}
