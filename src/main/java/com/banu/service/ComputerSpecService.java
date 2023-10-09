package com.banu.service;

import com.banu.repository.ComputerSpecRepository;
import com.banu.repository.entity.ComputerSpec;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.util.List;

public class ComputerSpecService {

    ComputerSpecRepository computerSpecRepository;

    public ComputerSpecService(){
        this.computerSpecRepository=new ComputerSpecRepository();
    }

    public ComputerSpec save(ComputerSpec entity) {
        return computerSpecRepository.save(entity);
    }

    public List<ComputerSpec> findAll(){
        return  computerSpecRepository.findALl();
    }
}
