package com.example.springboot.project.repository;

import com.example.springboot.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // All CRUD operations will be inherited from JpaRepository
}


