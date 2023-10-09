package com.banu.controller;


import com.banu.repository.entity.ComputerSpec;
import com.banu.service.ComputerSpecService;
import com.banu.utility.Constants;
import com.banu.utility.UtilityClass;

import java.util.List;

public class ComputerSpecContoller {

    ComputerSpecService computerSpecService;

    public ComputerSpecContoller(){
        this.computerSpecService=new ComputerSpecService();
    }

    public ComputerSpec createSpec(){
        String bellek = UtilityClass.stringDeger("Bellek = ");
        String isletimSistemi = UtilityClass.stringDeger("İsletim Sistemi = ");
        Integer islemciNesli = UtilityClass.intDeger("İslemci Nesli = ");
        String renk = UtilityClass.stringDeger("Renk = ");
        String islemciTipi = UtilityClass.stringDeger("İslemci Tipi = ");
        Integer ram = UtilityClass.intDeger("Ram = ");
        ComputerSpec computerSpec = ComputerSpec.builder()
                .bellek(bellek)
                .isletimSistemi(isletimSistemi)
                .islemciNesli(islemciNesli)
                .renk(renk)
                .islemciTipi(islemciTipi)
                .ram(ram)
                .baseEntity(Constants.getBaseEntity())
                .build();
        computerSpecService.save(computerSpec);
        return computerSpec;
    }

    public List<ComputerSpec> findAll(){
        return computerSpecService.findAll();
    }
}
