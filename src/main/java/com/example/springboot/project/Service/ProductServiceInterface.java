package com.example.springboot.project.Service;

import com.example.springboot.project.dto.ProductDTO;

import java.util.List;

public interface ProductServiceInterface {


    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO, Long categoryId);

    ProductDTO getProductById(long id);

    ProductDTO updateProduct(long id, ProductDTO productDTO, Long categoryId);

    void deleteProduct(long id);

}
