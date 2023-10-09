package com.banu.repository;

import com.banu.repository.entity.ComputerSpec;
import com.banu.utility.MyFactoryRepository;

public class ComputerSpecRepository extends MyFactoryRepository<ComputerSpec,Long> {

    public ComputerSpecRepository(){
        super(new ComputerSpec());
    }



}
