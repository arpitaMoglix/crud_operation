package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceInterface {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(long id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(long id, CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
