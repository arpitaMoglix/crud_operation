package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CategoryDtoForGetDelete;
import com.example.springboot.project.dto.CategoryDtoForPostPut;

import java.util.List;

public interface CategoryServiceInterface {

    List<CategoryDtoForGetDelete> getAllCategories();

    CategoryDtoForGetDelete getCategoryById(long id);

    CategoryDtoForGetDelete createCategory(CategoryDtoForPostPut categoryDTO);

    CategoryDtoForGetDelete updateCategory(long id, CategoryDtoForPostPut categoryDTO);

    void deleteCategory(long id);
}

