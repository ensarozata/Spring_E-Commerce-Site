package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;


    private Long pid;

    @Lob
    private String file;

}