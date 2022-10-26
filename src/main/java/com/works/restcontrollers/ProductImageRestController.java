package com.works.restcontrollers;

import com.works.entities.ProductImage;
import com.works.services.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@CrossOrigin
public class ProductImageRestController {

    final ProductImageService pService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ProductImage productImage) {
        return pService.add(productImage);
    }

    @GetMapping("/list/{pid}")
    public ResponseEntity list(@PathVariable long pid) {
        return pService.list(pid);
    }

    @GetMapping("/delete/{iid}")
    public ResponseEntity delete(@PathVariable long iid) {
        return pService.delete(iid);
    }



}