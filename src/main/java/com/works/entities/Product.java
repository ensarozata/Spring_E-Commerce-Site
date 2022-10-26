package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
public class Product extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer pid;

    @NotBlank(message = "Ürün İsmi Boş Bırakılamaz.")
    @Length(message = "Ürün İsmi En az Üç Karakter Giriniz.",min = 3)
    private String productName;

    @NotBlank(message = "Boş Bırakılamaz.")
    private String productDetail;

    @NotNull
    private int productPrice;

    private Integer price;
    private Integer stockQuantity;
    private String imagePath;

    @ManyToOne
    private Category category;
}
