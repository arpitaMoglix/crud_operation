package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CategoryDTO;
import com.example.springboot.project.dto.ProductDTO;
import com.example.springboot.project.dto.ProductDtoForCategory;
import com.example.springboot.project.entities.Category;
import com.example.springboot.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return mapToDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return mapToDTO(category);
    }

    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        existingCategory.setCategoryName(categoryDTO.getCategoryName());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapToDTO(updatedCategory);
    }

    public void deleteCategory(long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            // Handle the case where the category with the given id doesn't exist
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        return category;
    }


    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());

        // Mapping products associated with the category
        List<ProductDtoForCategory> productDTOs = category.getProducts().stream()
                .map(product -> {
                    ProductDtoForCategory ProductDtoForCategory= new ProductDtoForCategory();
                    ProductDtoForCategory.setProductId(product.getProductId());

                    return ProductDtoForCategory;
                })
                .collect(Collectors.toList());
        dto.setProducts(productDTOs);

        return dto;
    }



}
