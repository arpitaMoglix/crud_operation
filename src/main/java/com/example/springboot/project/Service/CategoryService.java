package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CategoryDtoForGetDelete;
import com.example.springboot.project.dto.CategoryDtoForPostPut;
import com.example.springboot.project.dto.ProductDtoWithIdOnly;
import com.example.springboot.project.entities.Category;
import com.example.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceInterface{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDtoForGetDelete> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CategoryDtoForGetDelete getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return mapToDTO(category);
    }

    public CategoryDtoForGetDelete createCategory(CategoryDtoForPostPut categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return mapToDTO(category);
    }

    public CategoryDtoForGetDelete updateCategory(long id, CategoryDtoForPostPut categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        existingCategory.setCategoryName(categoryDTO.getCategoryName());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapToDTO(updatedCategory);
    }

    public void deleteCategory(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }


    private Category mapToEntity(CategoryDtoForPostPut dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        Date now = new Date(); // Current timestamp
        category.setCreatedAt(now);
        category.setUpdatedAt(now);
        return category;
    }


    private CategoryDtoForGetDelete mapToDTO(Category category) {
        CategoryDtoForGetDelete dto = new CategoryDtoForGetDelete();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());



        //if products list is not null
        if (category.getProducts() != null) {
            // Mapping products associated with the category
            List<ProductDtoWithIdOnly> productDTOs = category.getProducts().stream()
                    .map(product -> {
                        ProductDtoWithIdOnly productDto = new ProductDtoWithIdOnly();
                        productDto.setProductId(product.getId());

                        return productDto;
                    })
                    .collect(Collectors.toList());
            dto.setProducts(productDTOs);
        } else {
            // Handling the case where products list is null
            dto.setProducts(Collections.emptyList());
        }

        return dto;
    }

}

