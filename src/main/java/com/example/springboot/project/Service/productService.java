package com.example.springboot.project.Service;

import com.example.springboot.project.dto.ProductDTO;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.entities.Category;
import com.example.springboot.project.repository.ProductRepository;
import com.example.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class productService implements ProductServiceInterface {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    // product DTO to entity


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Product product = mapToEntity(productDTO);

        product.setCategory(category);

        product = productRepository.save(product);

        return mapToDTO(product);
    }



    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    public ProductDTO updateProduct(long id, ProductDTO productDTO, Long categoryId) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Check if the category ID has changed
        if (existingProduct.getCategory() == null || !existingProduct.getCategory().getId().equals(categoryId)) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            existingProduct.setCategory(category);
        }

        // Update other product details
        existingProduct.setName(productDTO.getProductName());
        existingProduct.setPrice(productDTO.getProductPrice());
        existingProduct.setDescription(productDTO.getProductDescription());

        // Update updatedAt with the current timestamp
        existingProduct.setUpdatedAt(new Date());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }


    public void deleteProduct(long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            // Handle the case where the product with the given id doesn't exist
            throw new RuntimeException("Product not found with id: " + id);
        };
    }

    private Product mapToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getProductName());
        product.setPrice(dto.getProductPrice());
        product.setDescription(dto.getProductDescription());
        Date now = new Date(); // Current timestamp
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        return product;
    }




    // product entity to DTO

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setProductDescription(product.getDescription());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getCategoryName());
        }

        return dto;
    }
}
