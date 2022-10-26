package com.works.services;

import com.works.entities.Category;
import com.works.entities.Product;
import com.works.repositories.CategoryRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public ResponseEntity save(Product product) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        Optional<Product> optionalProduct = productRepository.findByProductNameEqualsIgnoreCase(product.getProductName());
        if (optionalProduct.isPresent()) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu Product Mevcuttur.");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        } else {
            hm.put(REnum.status, true);
            hm.put(REnum.result, productRepository.save(product));

            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

    public ResponseEntity list() {
        Map<Object, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result,productRepository.findAll());
        return new ResponseEntity(hm,HttpStatus.OK);

    }
    public ResponseEntity delete(Product product){
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Product> oldProduct=productRepository.findById(product.getPid());
            if (oldProduct.isPresent()){
                productRepository.deleteById(product.getPid());
                hm.put(REnum.status,true);
                hm.put(REnum.message,"Silme İşlemi Başarı ile Gerçekleşmiştir");
                return new ResponseEntity(hm,HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Böyle Bir Product Bulunamadı");
                return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){

            hm.put(REnum.status,false);
            hm.put(REnum.error,exception.getMessage());
        }

        return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity update(Product product){
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {

            Optional<Product> oldProdcut=productRepository.findById(product.getPid());
            if (oldProdcut.isPresent()){
               productRepository.saveAndFlush(product);

                hm.put(REnum.status,true);
                hm.put(REnum.result,product);
                return new ResponseEntity(hm,HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Böyle Bir Product Bulunamadı");
                return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            hm.put(REnum.status,false);
            hm.put(REnum.message,exception.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity productSearch(Integer q){
        Map<Object,Object> hm=new LinkedHashMap<>();
        Optional<Product> productList=productRepository.findById(q);

        hm.put(REnum.status,true);
        hm.put(REnum.result,productList);

        return new ResponseEntity(hm,HttpStatus.OK);

    }
    public ResponseEntity productsofcategory(String categoryName){
        Map<Object,Object> hm=new LinkedHashMap<>();
      Optional<Category> ocategory=categoryRepository.findByCategoryNameEqualsIgnoreCase(categoryName);
      if (ocategory.isPresent()){
          List<Product>productList=productRepository.findByCategory_CidEquals(ocategory.get().getCid());
          hm.put(REnum.status,true);
          hm.put(REnum.result,productList);
          return new ResponseEntity(hm,HttpStatus.OK);

      }else {
          hm.put(REnum.status,true);
          hm.put(REnum.message,"Böyle Bir Category Yok");
          return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
      }

    }
}
