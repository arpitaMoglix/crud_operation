package com.example.springboot.project.controller;

import com.example.springboot.project.Service.CategoryServiceInterface;
import com.example.springboot.project.dto.CategoryDtoForGetDelete;
import com.example.springboot.project.dto.CategoryDtoForPostPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceInterface categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDtoForGetDelete>> getAllCategories() {
        List<CategoryDtoForGetDelete> categoryDTOs = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDtoForGetDelete> getCategoryById(@PathVariable long id) {
        CategoryDtoForGetDelete categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDtoForGetDelete> createCategory(@RequestBody CategoryDtoForPostPut categoryDTO) {

            CategoryDtoForGetDelete createdCategoryDTO = categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDtoForGetDelete> updateCategory(@PathVariable long id, @RequestBody CategoryDtoForPostPut categoryDTO) {
        CategoryDtoForGetDelete updatedCategoryDTO = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + id);
        }
    }
}

