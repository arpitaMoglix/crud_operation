package com.example.springboot.project.Service;

import com.example.springboot.project.dto.productDTO;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.entities.Category;
import com.example.springboot.project.repository.ProductRepository;
import com.example.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyServices implements ServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Method to save product DTO
    public productDTO saveProduct(productDTO dto, Long categoryId) {
        Product product = mapToEntity(dto);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    // product DTO to entity
    private Product mapToEntity(productDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductDescription(dto.getProductDescription());
        return product;
    }

    // product entity to DTO
    private productDTO mapToDTO(Product product) {
        productDTO dto = new productDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductDescription(product.getProductDescription());

        // Ensure the category is not null before accessing its properties
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getCategoryName());
        }

        return dto;
    }

    public List<productDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public productDTO createProduct(productDTO productDTO, Long categoryId) {
        // Retrieve the Category from the repository
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Map productDTO to Product entity
        Product product = mapToEntity(productDTO);

        // Associate the Category with the Product
        product.setCategory(category);

        // Save the Product
        Product savedProduct = productRepository.save(product);

        // Map the saved Product back to productDTO and return
        return mapToDTO(savedProduct);
    }


    public productDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    public productDTO updateProduct(long id, productDTO productDTO, Long categoryId) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setProductPrice(productDTO.getProductPrice());
        existingProduct.setProductDescription(productDTO.getProductDescription());

        // Set the category again for the updated product
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}