package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer cid;

    @NotBlank(message = "Kategori Boş Bırakılamaz.")
    @Length(message = "Kategoriye Minimum üç karakter giriniz.",min = 3)
    private String categoryName;
}
