package com.banu.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_computer_spec")
public class ComputerSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bellek;

    private String isletimSistemi;

    private int islemciNesli;

    private String renk;

    private String islemciTipi;

    private Long islemciHizi;

    private int ram;

    @Embedded
    private BaseEntity baseEntity;

}
