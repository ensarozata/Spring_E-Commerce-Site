package com.works.repositories;


import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductNameEqualsIgnoreCase(String productName);

    List<Product> findByProductNameContainsIgnoreCase(String productName);

    List<Product> findByCategory_CidEquals(Integer cid);






}