package com.banu.service;

import com.banu.repository.ComputerRepository;
import com.banu.repository.entity.Computer;
import com.banu.repository.entity.ComputerSpec;

import java.util.List;

public class ComputerService {

    ComputerRepository computerRepository;

    public ComputerService(){
        this.computerRepository=new ComputerRepository();
    }
    public Computer save(Computer entity) {
        return  computerRepository.save(entity);
    }

    public List<Computer> findAll(){
        return computerRepository.findALl();
    }
}
