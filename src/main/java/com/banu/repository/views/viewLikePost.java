package com.banu.repository.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class viewLikePost {
    @Id
    Long id;

   private String computerid;
   private Long userid;
   private Long likecount;


}
