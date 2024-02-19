package com.example.springboot.project.Service;

import com.example.springboot.project.dto.ProductDTO;
import com.example.springboot.project.entities.Product;
import com.example.springboot.project.entities.Category;
import com.example.springboot.project.repository.ProductRepository;
import com.example.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Retrieve the Category from the repository
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Map productDTO to Product entity
        Product product = mapToEntity(productDTO);

        // Associate the Category with the Product
        product.setCategory(category);

        // Save the Product
        product = productRepository.save(product);

        // Map the saved Product back to productDTO and return
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
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setProductPrice(productDTO.getProductPrice());
        existingProduct.setProductDescription(productDTO.getProductDescription());

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
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setProductDescription(dto.getProductDescription());
        return product;
    }

    // product entity to DTO
    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
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
}