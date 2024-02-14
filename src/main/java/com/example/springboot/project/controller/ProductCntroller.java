package com.example.springboot.project.controller;

import com.example.springboot.project.exception.ResourseNotFoundException;
import com.example.springboot.project.model.Product;
import com.example.springboot.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCntroller {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    //create product restApi

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    //build get product by Id Rest API
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("product not found by id"+" "+id));

        return ResponseEntity.ok(p);
    }

    //build update product RestAPI
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product productDetails){
             Product updatePro = productRepository.findById(id).orElseThrow(() -> new
                     ResourseNotFoundException("product not found with the id :"+id));

             updatePro.setProductName(productDetails.getProductName());
             updatePro.setProductPrice(productDetails.getProductPrice());
             updatePro.setProductDescription(productDetails.getProductDescription());

             productRepository.save(updatePro);
             return ResponseEntity.ok(updatePro);
    }

    //build delete product RestAPI
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        Product deletePro = productRepository.findById(id).orElseThrow(() -> new
                ResourseNotFoundException("product not found with the id :"+id));
        productRepository.delete(deletePro);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    


}
