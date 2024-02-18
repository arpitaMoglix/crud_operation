package com.example.springboot.project.Service;

import com.example.springboot.project.dto.productDTO;

import java.util.List;

public interface ServiceInterface {


    List<productDTO> getAllProducts();

    productDTO createProduct(productDTO productDTO, Long categoryId);

    productDTO getProductById(long id);

    productDTO updateProduct(long id, productDTO productDTO, Long categoryId);

    void deleteProduct(long id);

}
