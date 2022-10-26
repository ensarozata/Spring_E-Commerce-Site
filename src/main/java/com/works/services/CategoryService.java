package com.works.services;

import com.works.entities.Category;
import com.works.repositories.CategoryRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity save(Category category) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        Optional<Category> optionalCategory = categoryRepository.findByCategoryNameEqualsIgnoreCase(category.getCategoryName());
        if (optionalCategory.isPresent()) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu Category Mevcuttur.");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        } else {
            hm.put(REnum.status, true);
            hm.put(REnum.result, categoryRepository.save(category));

            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

    public ResponseEntity list() {
        Map<Object, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result,categoryRepository.findAll());
        return new ResponseEntity(hm,HttpStatus.OK);

    }
    public ResponseEntity delete(Category category){
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Category> oldCategory=categoryRepository.findById(category.getCid());
            if (oldCategory.isPresent()){
                categoryRepository.deleteById(category.getCid());
                hm.put(REnum.status,true);
                hm.put(REnum.message,"Silme İşlemi Başarı ile Gerçekleşmiştir");
                return new ResponseEntity(hm,HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Böyle Bir Category Bulunamadı");
                 return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){

            hm.put(REnum.status,false);
            hm.put(REnum.error,exception.getMessage());
        }

        return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity update(Category category){
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {

            Optional<Category> oldCategory=categoryRepository.findById(category.getCid());
            if (oldCategory.isPresent()){
                categoryRepository.saveAndFlush(category);

                hm.put(REnum.status,true);
                hm.put(REnum.result,category);
                return new ResponseEntity(hm,HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Böyle Bir Category Bulunamadı");
                return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            hm.put(REnum.status,false);
            hm.put(REnum.message,exception.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }

    }
}