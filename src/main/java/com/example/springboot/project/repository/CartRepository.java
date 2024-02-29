package com.example.springboot.project.repository;

import com.example.springboot.project.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByIdAndUserId(Long cartId, Long userId);
}