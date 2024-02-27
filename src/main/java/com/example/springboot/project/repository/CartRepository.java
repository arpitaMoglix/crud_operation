package com.example.springboot.project.repository;

import com.example.springboot.project.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}