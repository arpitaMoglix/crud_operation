package com.example.springboot.project.controller;

import com.example.springboot.project.Service.CartServiceInterface;
import com.example.springboot.project.dto.CartDtoRequest;
import com.example.springboot.project.dto.CartDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceInterface cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDtoResponse> getCartById(@PathVariable Long cartId) {
        CartDtoResponse cartDtoResponse = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartDtoResponse);
    }



//    @PostMapping
//    public ResponseEntity<Long> createCart() {
//        Long createdCartId = cartService.createCart();
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartId);
//    }

//    @PostMapping("/{cartId}/addProduct")
//    public ResponseEntity<CartDtoResponse> addProductToCart(@PathVariable Long cartId, @RequestBody CartDtoRequest cartDtoRequest) {
//        CartDtoResponse addedCartItemDTO = cartService.addProductToCartV1(cartId, cartDtoRequest);
//        return new ResponseEntity<>(addedCartItemDTO, HttpStatus.CREATED);
//    }


//    @DeleteMapping("/{cartId}")
//    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartId) {
//        cartService.removeProductFromCart(cartId);
//        return ResponseEntity.noContent().build();
//    }

}

