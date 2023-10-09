package com.banu.controller;

import com.banu.service.PhotosService;

public class PhotosController {

    PhotosService photosService;

    public PhotosController(){
        this.photosService=new PhotosService();
    }
}
