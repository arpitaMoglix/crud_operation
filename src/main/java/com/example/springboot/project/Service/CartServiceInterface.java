package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CartDTO;

public interface CartServiceInterface {
    CartDTO getCartById(Long cartId);

    void clearCart(Long cartId);

    Long createCart();
}
