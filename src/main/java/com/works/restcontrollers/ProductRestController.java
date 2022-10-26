package com.works.restcontrollers;

import com.works.entities.Product;

import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {
    final ProductService productService;


    public ProductRestController(ProductService productService) {
        this.productService = productService;

    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product){

        return productService.save(product);
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return productService.list();
    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Product product){
        return productService.delete(product);
    }
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Product product){
        return productService.update(product);
    }
    @GetMapping("/productSearch")
        public ResponseEntity productSearch(@RequestParam(defaultValue = "")  Integer q){
            return productService.productSearch(q);
        }
    @PostMapping("/productsofcategory")
    public ResponseEntity productsofcategory(@RequestParam String categoryName){
        return productService.productsofcategory(categoryName);
    }

    }

