package com.banu.utility;

import com.banu.repository.entity.BaseEntity;

public class Constants {

    public static BaseEntity getBaseEntity(){
        return BaseEntity.builder()
                .ceratat(System.currentTimeMillis())
                .updateat(System.currentTimeMillis())
                .state(true).build();
    }
}
