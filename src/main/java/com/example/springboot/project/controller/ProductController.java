package com.example.springboot.project.controller;

import com.example.springboot.project.Service.MyServices;
import com.example.springboot.project.dto.productDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private MyServices productService;

    @GetMapping
    public ResponseEntity<List<productDTO>> getAllProducts() {
        List<productDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping
    public ResponseEntity<productDTO> createProduct(@RequestBody productDTO productDTO) {
        productDTO createdProductDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDTO> getProductById(@PathVariable long id) {
        productDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<productDTO> updateProduct(@PathVariable long id, @RequestBody productDTO productDTO) {
        productDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
