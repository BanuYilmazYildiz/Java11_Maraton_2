package com.banu.repository;

import com.banu.repository.entity.Computer;
import com.banu.utility.MyFactoryRepository;

public class ComputerRepository extends MyFactoryRepository<Computer,Long> {

    public ComputerRepository(){
        super(new Computer());
    }
}
