package com.example.springboot.project.repository;

import com.example.springboot.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // All CRUD operations will be inherited from JpaRepository
    List<Product> findByCategory_Id(Long id);
}




