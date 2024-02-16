package com.example.springboot.project.Service;

import com.example.springboot.project.dto.productDTO;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyServices {

    @Autowired
    private ProductRepository productRepository;

    // Method to save product DTO
    public productDTO saveProduct(productDTO dto) {
        Product product = mapToEntity(dto);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    // Method to map product DTO to entity
    private Product mapToEntity(productDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductDescription(dto.getProductDescription());
        return product;
    }

    // Method to map product entity to DTO
    private productDTO mapToDTO(Product product) {
        productDTO dto = new productDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductDescription(product.getProductDescription());
        return dto;
    }

    public List<productDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public productDTO createProduct(productDTO productDTO) {
        return saveProduct(productDTO);
    }

    public productDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    public productDTO updateProduct(long id, productDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setProductPrice(productDTO.getProductPrice());
        existingProduct.setProductDescription(productDTO.getProductDescription());
        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }


}
