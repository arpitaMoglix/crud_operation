package com.example.springboot.project.controller;

import com.example.springboot.project.Service.CartItemServiceInterface;
import com.example.springboot.project.dto.CartItemDtoRequest;
import com.example.springboot.project.dto.CartItemDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    @Autowired
    private CartItemServiceInterface cartItemService;

    @PostMapping("/{cartId}")
    public ResponseEntity<CartItemDtoResponse> addProductToCart(@PathVariable Long cartId, @RequestBody CartItemDtoRequest cartItemDTO) {
        CartItemDtoResponse addedCartItemDTO = cartItemService.addProductToCart(cartId, cartItemDTO);
        return new ResponseEntity<>(addedCartItemDTO, HttpStatus.CREATED);
    }



    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeProductFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }
}

