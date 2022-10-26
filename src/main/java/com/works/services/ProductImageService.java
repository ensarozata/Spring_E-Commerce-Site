package com.works.services;

import com.works.entities.ProductImage;
import com.works.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    final ProductImageRepository pRepo;

    public ResponseEntity add(ProductImage productImage) {
        pRepo.save(productImage);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", productImage);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity list( long pid ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", pRepo.findByPidEquals(pid));
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity delete( long iid ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            pRepo.deleteById(iid);
            hm.put("status", true);
        }catch (Exception ex) {
            hm.put("status", false);
        }
        hm.put("result", iid);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}