package com.example.springboot.project.controller;

import com.example.springboot.project.Service.MyServices;
import com.example.springboot.project.Service.ServiceInterface;
import com.example.springboot.project.dto.productDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ServiceInterface productService;

    @GetMapping
    public ResponseEntity<List<productDTO>> getAllProducts() {
        List<productDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping
    public ResponseEntity<productDTO> createProduct(@RequestBody productDTO productDTO, @RequestParam Long categoryId) {
        productDTO createdProductDTO = productService.createProduct(productDTO, categoryId);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDTO> getProductById(@PathVariable long id) {
        productDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<productDTO> updateProduct(@PathVariable long id, @RequestBody productDTO productDTO, @RequestParam Long categoryId) {
        productDTO updatedProductDTO = productService.updateProduct(id, productDTO, categoryId);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

