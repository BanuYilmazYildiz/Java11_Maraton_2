package com.banu.controller;

import com.banu.repository.entity.Computer;
import com.banu.service.ComputerService;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.util.List;

public class ComputerController {

    ComputerService computerService;

    ComputerSpecContoller computerSpecContoller;

    public ComputerController(){
        this.computerService=new ComputerService();
        this.computerSpecContoller = new ComputerSpecContoller();
    }

    public Computer createComputer(){
        String marka = UtilityClass.stringDeger("Marka = ");
        String model = UtilityClass.stringDeger("Model = ");
        computerSpecContoller.findAll().forEach(System.out::println);
        Long computerspecid = UtilityClass.longDeger("Eklemek istediğiniz özellik id sini secin : ");
        Computer computer = Computer.builder()
                .marka(marka)
                .model(model)
                .computerspecid(computerspecid)
                .baseEntity(Constants.getBaseEntity())
                .build();
        return computerService.save(computer);
    }

    public List<Computer> findAll(){
        return computerService.findAll();
    }
}
