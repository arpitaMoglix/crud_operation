package com.example.springboot.project.Service;

import com.example.springboot.project.dto.CartItemDtoRequest;
import com.example.springboot.project.dto.CartItemDtoResponse; // Import the CartItemDtoResponse

public interface CartItemServiceInterface {
    CartItemDtoResponse addProductToCart(Long cartId, CartItemDtoRequest cartItemDTO); // Change the return type

    void removeProductFromCart(Long cartItemId);
}
